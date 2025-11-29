package webcrawler.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebCrawlerJob {
    private String jobId;
    private JobStatus jobStatus;
    private Integer maxDepth;
    private List<String> urls;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static WebCrawlerJob newInstance(String jobId, Integer maxDepth, List<String> urls) {
        LocalDateTime now = LocalDateTime.now();
        return new WebCrawlerJob(jobId, JobStatus.PENDING, maxDepth, urls, now, now);
    }
}
