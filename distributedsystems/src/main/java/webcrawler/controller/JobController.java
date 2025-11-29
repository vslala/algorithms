package webcrawler.controller;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webcrawler.models.JobRequest;
import webcrawler.models.JobRequestResponse;
import webcrawler.models.JobResult;
import webcrawler.models.WebCrawlerJob;
import webcrawler.services.JobIngestService;

import java.util.Map;
import java.util.Objects;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobIngestService jobIngestService;
    private final Map<String, Integer> concurrentStatusCache;
    private final Cache<String, Integer> jobStatusCache;

    @Autowired
    public JobController(JobIngestService jobIngestService, Map<String, Integer> concurrentStatusCache, Cache<String, Integer> jobStatusCache) {
        this.jobIngestService = jobIngestService;
        this.concurrentStatusCache = concurrentStatusCache;
        this.jobStatusCache = jobStatusCache;
    }

    @PostMapping
    public JobRequestResponse submitJobs(@RequestBody JobRequest request) {
        WebCrawlerJob job = this.jobIngestService.submitJob(request);
        return new JobRequestResponse(job.getJobId(), job.getCreatedAt());
    }

    @GetMapping("/{jobId}/status")
    public Map<String, Integer> getStatus(@PathVariable("jobId") String jobId) {
        final String inProgressKey = jobId + "::IN_PROGRESS";
        final String completedKey = jobId + "::COMPLETED";
        return Map.of(
                "IN_PROGRESS", Objects.requireNonNull(this.jobStatusCache.get(inProgressKey, k -> 0)),
                "COMPLETED", Objects.requireNonNull(this.jobStatusCache.get(completedKey, k -> 0))
        );
    }

    @GetMapping("/{jobId}/results")
    public JobResult getResults(
            @PathVariable("jobId") String jobId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "100") int pageSize) {
        JobResult result = this.jobIngestService.getResults(jobId, page, pageSize);
        return result;
    }
}
