package webcrawler.components;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import webcrawler.models.JobStatus;
import webcrawler.repositories.entities.JobUrl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 28/11/2025
 */
@Order(1)
@Component
@Log4j2
class CheckDuplicateUrlTask implements Task {

    private final Cache<String, Set<String>> urlCache;

    @Autowired
    public CheckDuplicateUrlTask(@Qualifier("url_cache") Cache<String, Set<String>> urlCache) {
        this.urlCache = urlCache;
    }

    @Override
    public TaskContext execute(TaskContext taskContext) {
        JobUrl jobUrl = taskContext.getJobUrl();
        log.info("Checking duplicate url: {}", jobUrl.getUrl());

        Set<String> seenUrls = this.urlCache.get(jobUrl.getJob().getJobId(), k -> Collections.synchronizedSet(new HashSet<>()));
        assert seenUrls != null;
        if (seenUrls.contains(jobUrl.getUrl())) {
            log.info("Duplicate URL found, marking for completion: {}", jobUrl.getUrl());
            taskContext.setFinalStatus(JobStatus.COMPLETED);
            taskContext.setTerminate(true);
        } else {
            seenUrls.add(jobUrl.getUrl());
            this.urlCache.put(jobUrl.getJob().getJobId(), seenUrls);
        }

        return taskContext;
    }
}
