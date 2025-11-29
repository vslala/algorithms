package webcrawler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import webcrawler.models.JobRequest;
import webcrawler.models.JobStatus;
import webcrawler.models.WebCrawlerJob;
import webcrawler.services.JobIngestService;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class JobControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private JobIngestService jobIngestService;

    @InjectMocks
    private JobController jobController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    }

    @Test
    void shouldSubmitJobSuccessfully() throws Exception {
        // Given
        JobRequest request = new JobRequest();
        request.setUrls(Arrays.asList("https://example.com"));
        request.setMaxDepth(2);

        WebCrawlerJob job = new WebCrawlerJob(
            "job-123",
            JobStatus.PENDING,
            2,
            Arrays.asList("https://example.com"),
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        when(jobIngestService.submitJob(any(JobRequest.class))).thenReturn(job);

        // When & Then
        mockMvc.perform(post("/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.jobId").value("job-123"))
            .andExpect(jsonPath("$.createdAt").exists());
    }

}
