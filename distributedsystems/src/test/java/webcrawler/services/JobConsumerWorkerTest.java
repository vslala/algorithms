package webcrawler.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import webcrawler.models.JobStatus;
import webcrawler.models.WebCrawlerJob;
import webcrawler.repositories.JobRepository;
import webcrawler.repositories.JobUrlRepository;
import webcrawler.repositories.entities.JobEntity;
import webcrawler.repositories.entities.JobUrl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobConsumerWorkerTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobUrlRepository jobUrlRepository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private java.util.List<webcrawler.components.Task> workerTasks;

    private ObjectMapper objectMapper;
    private JobConsumerWorker jobConsumerWorker;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Cache<String, Integer> jobStatusCache = Caffeine.newBuilder().build();
        jobConsumerWorker = new JobConsumerWorker(jobRepository, jobUrlRepository, objectMapper, kafkaTemplate, workerTasks, jobStatusCache);
    }

    @Test
    void shouldConsumeJobAndSaveToDatabase() throws Exception {
        // Given
        WebCrawlerJob job = new WebCrawlerJob(
            "job-123",
            JobStatus.PENDING,
            2,
            Arrays.asList("https://example.com", "https://test.com"),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        String jobJson = objectMapper.writeValueAsString(job);

        JobEntity savedEntity = new JobEntity();
        savedEntity.setJobId("job-123");
        savedEntity.setMaxDepth(2);

        JobUrl jobUrl1 = new JobUrl();
        jobUrl1.setId(1L);
        jobUrl1.setUrl("https://example.com");
        jobUrl1.setUrlType(JobUrl.UrlType.URL);
        jobUrl1.setStatus(JobStatus.PENDING);
        jobUrl1.setDepth(0);
        jobUrl1.setParentUrlId(null);
        jobUrl1.setJob(savedEntity);

        JobUrl jobUrl2 = new JobUrl();
        jobUrl2.setId(2L);
        jobUrl2.setUrl("https://test.com");
        jobUrl2.setUrlType(JobUrl.UrlType.URL);
        jobUrl2.setStatus(JobStatus.PENDING);
        jobUrl2.setDepth(0);
        jobUrl2.setParentUrlId(null);
        jobUrl2.setJob(savedEntity);

        savedEntity.setJobUrls(Arrays.asList(jobUrl1, jobUrl2));

        when(jobRepository.save(any(JobEntity.class))).thenReturn(savedEntity);

        // When
        jobConsumerWorker.consumeJob(jobJson);

        // Then
        verify(jobRepository, times(1)).save(any(JobEntity.class));
        verify(kafkaTemplate, times(2)).send(eq("url_frontier"), anyString());
    }

    @Test
    void shouldSkipJobWithNoUrls() throws Exception {
        // Given
        WebCrawlerJob job = new WebCrawlerJob(
            "job-123",
            JobStatus.PENDING,
            2,
            Arrays.asList(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        String jobJson = objectMapper.writeValueAsString(job);

        // When
        jobConsumerWorker.consumeJob(jobJson);

        // Then
        verify(jobRepository, never()).save(any(JobEntity.class));
        verify(kafkaTemplate, never()).send(anyString(), anyString());
    }

    @Test
    void shouldPushUrlsToKafkaAfterSaving() throws Exception {
        // Given
        WebCrawlerJob job = new WebCrawlerJob(
            "job-456",
            JobStatus.PENDING,
            3,
            Arrays.asList("https://example.com"),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        String jobJson = objectMapper.writeValueAsString(job);

        JobEntity savedEntity = new JobEntity();
        savedEntity.setJobId("job-456");
        savedEntity.setMaxDepth(3);
        JobUrl jobUrl = new JobUrl();
        jobUrl.setId(1L);
        jobUrl.setUrl("https://example.com");
        jobUrl.setUrlType(JobUrl.UrlType.URL);
        jobUrl.setStatus(JobStatus.PENDING);
        jobUrl.setDepth(0);
        jobUrl.setParentUrlId(null);
        jobUrl.setJob(savedEntity);
        savedEntity.setJobUrls(List.of(jobUrl));

        when(jobRepository.save(any(JobEntity.class))).thenReturn(savedEntity);

        // When
        jobConsumerWorker.consumeJob(jobJson);

        // Then
        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate, atLeastOnce()).send(topicCaptor.capture(), messageCaptor.capture());

        List<String> topics = topicCaptor.getAllValues();
        assertTrue(topics.contains("url_frontier"));
    }
}
