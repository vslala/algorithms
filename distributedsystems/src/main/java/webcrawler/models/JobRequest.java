package webcrawler.models;

import lombok.Data;

import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Data
public class JobRequest {
    private List<String> urls;
    private Integer maxDepth;
}
