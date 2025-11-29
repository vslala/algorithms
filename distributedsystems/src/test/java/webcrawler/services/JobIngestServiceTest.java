package webcrawler.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import webcrawler.models.JobRequest;
import webcrawler.models.WebCrawlerJob;
import webcrawler.repositories.JobRepository;
import webcrawler.repositories.JobUrlRepository;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobIngestServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobUrlRepository jobUrlRepository;

    private ObjectMapper objectMapper;
    private JobIngestService jobIngestService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        jobIngestService = new JobIngestService(kafkaTemplate, objectMapper, jobRepository, jobUrlRepository);
    }

    @Test
    void shouldSubmitJobAndSendToKafka() throws Exception {
        // Given
        JobRequest request = new JobRequest();
        request.setUrls(Arrays.asList("https://example.com", "https://test.com"));
        request.setMaxDepth(3);

        CompletableFuture<SendResult<String, String>> future = mock(CompletableFuture.class);
        SendResult<String, String> sendResult = mock(SendResult.class);
        when(kafkaTemplate.send(eq("web_crawler_jobs"), anyString())).thenReturn(future);
        when(future.get()).thenReturn(sendResult);

        // When
        WebCrawlerJob result = jobIngestService.submitJob(request);

        // Then
        assertNotNull(result);
        assertNotNull(result.getJobId());
        assertEquals(3, result.getMaxDepth());
        assertEquals(2, result.getUrls().size());
        verify(kafkaTemplate, times(1)).send(eq("web_crawler_jobs"), anyString());
    }

    @Test
    void shouldGenerateUniqueJobId() throws Exception {
        // Given
        JobRequest request = new JobRequest();
        request.setUrls(Arrays.asList("https://example.com"));
        request.setMaxDepth(2);

        CompletableFuture<SendResult<String, String>> future = mock(CompletableFuture.class);
        SendResult<String, String> sendResult = mock(SendResult.class);
        when(kafkaTemplate.send(anyString(), anyString())).thenReturn(future);
        when(future.get()).thenReturn(sendResult);

        // When
        WebCrawlerJob result1 = jobIngestService.submitJob(request);
        WebCrawlerJob result2 = jobIngestService.submitJob(request);

        // Then
        assertNotEquals(result1.getJobId(), result2.getJobId());
    }
}
