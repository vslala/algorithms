package webcrawler.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestResponse {
    private String jobId;
    private LocalDateTime createdAt;
}
