package webcrawler.components;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import webcrawler.models.JobStatus;
import webcrawler.repositories.entities.JobUrl;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 28/11/2025
 */
@Order(2)
@Component
@Log4j2
public class DownloadHTMLTask implements Task {

    @Override
    public TaskContext execute(TaskContext taskContext) {
        JobUrl jobUrl = taskContext.getJobUrl();
        log.info("Downloading HTML from: {}", jobUrl.getUrl());

        Document document;
        try {
            document = Jsoup.connect(jobUrl.getUrl())
                    .userAgent("Mozilla/5.0 (compatible; WebCrawler/1.0)")
                    .timeout(10000)
                    .ignoreHttpErrors(true)
                    .followRedirects(true)
                    .get();
            taskContext.setDocument(document);
        } catch (Exception e) {
            log.warn("Failed to download URL, marking as FAILED: {} - {}", jobUrl.getUrl(), e.getMessage());
            taskContext.setFinalStatus(JobStatus.FAILED);
            taskContext.setTerminate(true);
        }

        return taskContext;
    }
}
