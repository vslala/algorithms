package webcrawler.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSubmission {
    private String jobId;
    private List<String> urls;
    private int maxDepth;
}
