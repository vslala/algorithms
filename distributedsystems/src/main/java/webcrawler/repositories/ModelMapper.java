package webcrawler.repositories;

import webcrawler.models.JobStatus;
import webcrawler.models.WebCrawlerJob;
import webcrawler.repositories.entities.JobEntity;
import webcrawler.repositories.entities.JobUrl;

import java.util.stream.Collectors;

/**
 * Mapper to convert between domain models and JPA entities
 *
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
public class ModelMapper {

    /**
     * Convert WebCrawlerJob to JobEntity with JobUrl entities
     * All URLs are created with PENDING status at depth 0
     */
    public static JobEntity toEntity(WebCrawlerJob job) {
        JobEntity entity = new JobEntity();
        entity.setJobId(job.getJobId());
        entity.setStatus(JobStatus.valueOf(job.getJobStatus().toString()));
        entity.setMaxDepth(job.getMaxDepth());
        entity.setCreatedAt(job.getCreatedAt());
        entity.setUpdatedAt(job.getUpdatedAt());

        var jobUrls = job.getUrls().stream()
            .map(url -> {
                JobUrl jobUrl = new JobUrl();
                jobUrl.setUrl(url);
                jobUrl.setUrlType(JobUrl.UrlType.URL);
                jobUrl.setJob(entity);
                jobUrl.setStatus(JobStatus.PENDING);
                jobUrl.setDepth(0);
                return jobUrl;
            })
            .collect(Collectors.toList());

        entity.setJobUrls(jobUrls);

        return entity;
    }

    public static WebCrawlerJob toJob(JobEntity jobEntity) {
        var job = new WebCrawlerJob();
        job.setJobId(jobEntity.getJobId());
        job.setJobStatus(JobStatus.valueOf(jobEntity.getStatus().toString()));
        job.setMaxDepth(jobEntity.getMaxDepth());
        job.setUrls(jobEntity.getJobUrls().stream().map(JobUrl::getUrl).toList());
        job.setUpdatedAt(jobEntity.getUpdatedAt());
        job.setCreatedAt(jobEntity.getCreatedAt());

        return job;
    }
}
