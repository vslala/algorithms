package webcrawler.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import webcrawler.models.JobRequest;
import webcrawler.models.JobResult;
import webcrawler.models.PageInfo;
import webcrawler.models.WebCrawlerJob;
import webcrawler.repositories.JobRepository;
import webcrawler.repositories.JobUrlRepository;
import webcrawler.repositories.ModelMapper;
import webcrawler.repositories.entities.JobEntity;
import webcrawler.repositories.entities.JobUrl;

import java.util.List;
import java.util.UUID;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Service
public class JobIngestService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final JobRepository jobRepository;
    private final JobUrlRepository jobUrlRepository;

    @Autowired
    public JobIngestService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, JobRepository jobRepository, JobUrlRepository jobUrlRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.jobRepository = jobRepository;
        this.jobUrlRepository = jobUrlRepository;
    }

    @SneakyThrows
    public WebCrawlerJob submitJob(JobRequest request) {
        String jobId = UUID.randomUUID().toString();
        var newJob = WebCrawlerJob.newInstance(jobId, request.getMaxDepth(), request.getUrls());

        // Convert job to JSON string
        String jobJson = objectMapper.writeValueAsString(newJob);

        // Send JSON string to Kafka
        SendResult<String, String> result = this.kafkaTemplate.send("web_crawler_jobs", jobJson).get();

        return newJob;
    }

    public WebCrawlerJob getJob(String jobId) {
        JobEntity jobEntity = this.jobRepository.findByJobId(jobId).orElseThrow(() -> new RuntimeException("Job Id not found!"));
        return ModelMapper.toJob(jobEntity);
    }

    public JobResult getResults(String jobId, int page, int pageSize) {
        JobEntity jobEntity = jobRepository.findByJobId(jobId)
            .orElseThrow(() -> new RuntimeException("Job not found: " + jobId));

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<JobUrl> pagedUrls = jobUrlRepository.findByJobIdAndUrlTypeAndStatus(
            jobId, JobUrl.UrlType.URL, pageable);

        long totalUrls = jobUrlRepository.countByJobIdAndUrlTypeAndStatus(jobId, JobUrl.UrlType.URL);

        JobResult result = new JobResult();
        result.setJobId(jobId);
        result.setCurrentPage(page);
        result.setPageSize(pageSize);
        result.setTotalPages(pagedUrls.getTotalPages());
        result.setTotalUrls(totalUrls);

        List<JobUrl> allUrls = jobUrlRepository.findByJobId(jobId);

        for (JobUrl jobUrl : pagedUrls.getContent()) {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPageUrl(jobUrl.getUrl());
            pageInfo.setUrls(new java.util.ArrayList<>());
            pageInfo.setImages(new java.util.ArrayList<>());

            List<JobUrl> childUrls = allUrls.stream()
                .filter(u -> u.getParentUrlId() != null && u.getParentUrlId().equals(jobUrl.getId()))
                .toList();

            for (JobUrl child : childUrls) {
                if (child.getUrlType() == JobUrl.UrlType.URL) {
                    pageInfo.getUrls().add(child.getUrl());
                } else if (child.getUrlType() == JobUrl.UrlType.IMAGE) {
                    pageInfo.getImages().add(child.getUrl());
                }
            }

            result.addPageInfo(pageInfo);
        }

        return result;
    }
}
