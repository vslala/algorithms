package webcrawler.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobUrlMessage {
    private Long id;
    private String url;
    private String urlType;
    private String jobId;
    private String status;
    private Integer depth;
    private Long parentUrlId;
}
