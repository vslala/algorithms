package webcrawler.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import webcrawler.components.Task;
import webcrawler.components.TaskContext;
import webcrawler.models.JobStatus;
import webcrawler.models.JobUrlMessage;
import webcrawler.models.WebCrawlerJob;
import webcrawler.repositories.JobRepository;
import webcrawler.repositories.JobUrlRepository;
import webcrawler.repositories.ModelMapper;
import webcrawler.repositories.entities.JobEntity;
import webcrawler.repositories.entities.JobUrl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Kafka consumer worker that processes jobs from web_crawler_jobs topic
 * This worker starts automatically with the application and listens for incoming jobs
 *
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class JobConsumerWorker {

    private final JobRepository jobRepository;
    private final JobUrlRepository jobUrlRepository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final List<Task> workerTasks;
    private final Map<String, Integer> maxDepth = new ConcurrentHashMap<>();
    private final Cache<String, Integer> jobStatusCache;

    /**
     * Consumes jobs from the web_crawler_jobs Kafka topic
     *
     * @param jobJson The JSON string representation of the job
     */
    @SneakyThrows
    @KafkaListener(
            topics = "web_crawler_jobs",
            groupId = "web-crawler-consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    public void consumeJob(String jobJson) {
        try {
            // Deserialize JSON string to WebCrawlerJob object
            WebCrawlerJob job = objectMapper.readValue(jobJson, WebCrawlerJob.class);

            log.info("========================================");
            log.info("Received job from Kafka topic: web_crawler_jobs");
            log.info("Job ID: {}", job.getJobId());
            log.info("Job Status: {}", job.getJobStatus());
            log.info("Max Depth: {}", job.getMaxDepth());
            log.info("Number of URLs: {}", job.getUrls() != null ? job.getUrls().size() : 0);
            log.info("URLs to crawl: {}", job.getUrls());
            log.info("========================================");

            if (job.getUrls() == null || job.getUrls().isEmpty()) {
                log.warn("Job {} has no URLs to process, skipping", job.getJobId());
                return;
            }

            JobEntity jobEntity = ModelMapper.toEntity(job);
            JobEntity savedJob = jobRepository.save(jobEntity);

            log.info("Successfully saved job {} to database with {} URLs in PENDING status",
                    savedJob.getJobId(), savedJob.getJobUrls().size());
            this.maxDepth.put(savedJob.getJobId(), savedJob.getMaxDepth());
            for (JobUrl jobUrl : savedJob.getJobUrls()) {
                JobUrlMessage message = new JobUrlMessage(
                        jobUrl.getId(),
                        jobUrl.getUrl(),
                        jobUrl.getUrlType().name(),
                        savedJob.getJobId(),
                        jobUrl.getStatus().name(),
                        jobUrl.getDepth(),
                        jobUrl.getParentUrlId()
                );
                this.kafkaTemplate.send("url_frontier", this.objectMapper.writeValueAsString(message));
            }
        } catch (Exception e) {
            log.error("Failed to process job from Kafka", e);
            throw new RuntimeException("Failed to process job", e); // Rethrow to trigger Kafka retry
        }
    }

    @SneakyThrows
    @KafkaListener(
            topics = "url_frontier",
            groupId = "web-crawler-url-frontier-consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    public void consumeAndProcessUrl(String jobUrlJson) {
        JobUrlMessage message = this.objectMapper.readValue(jobUrlJson, JobUrlMessage.class);

        JobUrl jobUrl = jobUrlRepository.findByIdWithJob(message.getId())
                .orElseThrow(() -> new RuntimeException("JobUrl not found: " + message.getId()));

        final String completedKey = jobUrl.getJob().getJobId() + "::COMPLETED";
        final String inProgressKey = jobUrl.getJob().getJobId() + "::IN_PROGRESS";

        // Always increment IN_PROGRESS first, since URL is being processed
        jobUrl.setStatus(JobStatus.IN_PROGRESS);
        jobUrlRepository.save(jobUrl);
        this.jobStatusCache.put(inProgressKey, Objects.requireNonNull(this.jobStatusCache.get(inProgressKey, k -> 0)) + 1);

        if (message.getDepth() >= this.maxDepth.getOrDefault(message.getJobId(), 0)) {
            log.info("Skipping URL due to max depth limit: {} (depth: {}, max: {})",
                jobUrl.getUrl(), message.getDepth(), this.maxDepth.get(message.getJobId()));
            jobUrl.setStatus(JobStatus.COMPLETED);
            jobUrlRepository.save(jobUrl);

            // Update cache: decrement IN_PROGRESS, increment COMPLETED
            this.jobStatusCache.put(inProgressKey, Objects.requireNonNull(this.jobStatusCache.get(inProgressKey, k -> 0)) - 1);
            this.jobStatusCache.put(completedKey, Objects.requireNonNull(this.jobStatusCache.get(completedKey, k -> 0)) + 1);
            return;
        }

        log.debug("JobUrl: {}", objectMapper.writeValueAsString(jobUrl).indent(4));

        var taskContext = new TaskContext(jobUrl);
        try {
            for (Task workerTask : this.workerTasks) {
                log.info("Processing URL: {}", jobUrl.getUrl());
                taskContext = workerTask.execute(taskContext);
                if (taskContext.isTerminate()) {
                    break;
                }
            }

            // Centralized status and cache update after all tasks
            if (taskContext.getFinalStatus() != null) {
                jobUrl.setStatus(taskContext.getFinalStatus());
                jobUrlRepository.save(jobUrl);
                log.info("URL processing completed with status {}: {}", taskContext.getFinalStatus(), jobUrl.getUrl());
                // Update cache: decrement IN_PROGRESS, increment COMPLETED
                this.jobStatusCache.put(inProgressKey, Objects.requireNonNull(this.jobStatusCache.get(inProgressKey, k -> 0)) - 1);
                this.jobStatusCache.put(completedKey, Objects.requireNonNull(this.jobStatusCache.get(completedKey, k -> 0)) + 1);
                JobEntity job = jobRepository.findByJobId(message.getJobId()).orElseThrow();
                job.setStatus(taskContext.getFinalStatus());
                jobRepository.save(job);
            } else {
                log.info("Successfully processed URL: {}", jobUrl.getUrl());
            }
        } catch (Exception e) {
            log.error("Failed to process URL with exception: {} - {}", jobUrl.getUrl(), e.getMessage());
            jobUrl.setStatus(JobStatus.FAILED);
            jobUrlRepository.save(jobUrl);

            // Update cache: decrement IN_PROGRESS, increment COMPLETED
            this.jobStatusCache.put(inProgressKey, Objects.requireNonNull(this.jobStatusCache.get(inProgressKey, k -> 0)) - 1);
            this.jobStatusCache.put(completedKey, Objects.requireNonNull(this.jobStatusCache.get(completedKey, k -> 0)) + 1);
        }
    }
}
