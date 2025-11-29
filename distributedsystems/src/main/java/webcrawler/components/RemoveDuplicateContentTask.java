package webcrawler.components;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import webcrawler.models.JobStatus;
import webcrawler.repositories.entities.JobUrl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 28/11/2025
 */
@Order(3)
@Component
@Log4j2
public class RemoveDuplicateContentTask implements Task {

    private final Cache<String, Set<String>> pageContentCache;

    @Autowired
    public RemoveDuplicateContentTask(@Qualifier("page_content_cache") Cache<String, Set<String>> pageContentCache) {
        this.pageContentCache = pageContentCache;
    }

    @SneakyThrows
    @Override
    public TaskContext execute(TaskContext taskContext) {
        JobUrl jobUrl = taskContext.getJobUrl();
        String pageContentHash = generateHash(taskContext.getDocument().html());
        var uniquePageContent = this.pageContentCache.get(jobUrl.getJob().getJobId(), k -> Collections.synchronizedSet(new HashSet<>()));
        assert uniquePageContent != null;
        if (uniquePageContent.contains(pageContentHash)) {
            log.info("Duplicate content found, marking for completion: {}", jobUrl.getUrl());
            taskContext.setFinalStatus(JobStatus.COMPLETED);
            taskContext.setTerminate(true);
        } else {
            uniquePageContent.add(pageContentHash);
        }
        return taskContext;
    }

    /**
     * Generate SHA-256 hash of content for duplicate detection
     */
    private String generateHash(String content) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(content.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
