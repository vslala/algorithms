package webcrawler.components;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import webcrawler.models.JobStatus;
import webcrawler.repositories.entities.JobEntity;
import webcrawler.repositories.entities.JobUrl;

/**
* @author Varun Shrivastava
* @github www.github.com/vslala
* @date 28/11/2025
*/
@Data
@NoArgsConstructor
public class TaskContext {
    private JobEntity jobEntity;
    private JobUrl jobUrl;
    private Document document;
    private String pageContent;
    private boolean terminate;
    private JobStatus finalStatus;

    public TaskContext(JobUrl jobUrl) {
        this.jobUrl = jobUrl;
        this.terminate = false;
        this.finalStatus = null;
    }
}
