package webcrawler.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webcrawler.repositories.entities.JobUrl;

import java.util.List;

/**
 * Repository for JobUrl entity
 *
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Repository
public interface JobUrlRepository extends JpaRepository<JobUrl, Long> {

    /**
     * Find all URLs for a given job ID
     */
    @Query("SELECT ju FROM JobUrl ju WHERE ju.job.jobId = :jobId")
    List<JobUrl> findByJobId(String jobId);

    /**
     * Find URLs by job ID and status
     */
    @Query("SELECT ju FROM JobUrl ju WHERE ju.job.jobId = :jobId AND ju.status = :status")
    List<JobUrl> findByJobIdAndStatus(String jobId, JobUrl.Status status);

    /**
     * Count URLs by job ID and status
     */
    @Query("SELECT COUNT(ju) FROM JobUrl ju WHERE ju.job.jobId = :jobId AND ju.status = :status")
    long countByJobIdAndStatus(String jobId, JobUrl.Status status);

    /**
     * Find all image URLs for a given job ID
     */
    @Query("SELECT ju FROM JobUrl ju WHERE ju.job.jobId = :jobId AND ju.urlType = 'IMAGE'")
    List<JobUrl> findImagesByJobId(String jobId);

    /**
     * Find URLs by job ID and URL type with pagination
     */
    @Query("SELECT ju FROM JobUrl ju WHERE ju.job.jobId = :jobId AND ju.urlType = :urlType AND ju.status = 'COMPLETED'")
    Page<JobUrl> findByJobIdAndUrlTypeAndStatus(String jobId, JobUrl.UrlType urlType, Pageable pageable);

    /**
     * Count URLs by job ID and URL type
     */
    @Query("SELECT COUNT(ju) FROM JobUrl ju WHERE ju.job.jobId = :jobId AND ju.urlType = :urlType AND ju.status = 'COMPLETED'")
    long countByJobIdAndUrlTypeAndStatus(String jobId, JobUrl.UrlType urlType);

    /**
     * Find JobUrl by ID with eager fetch of job entity
     */
    @Query("SELECT ju FROM JobUrl ju JOIN FETCH ju.job WHERE ju.id = :id")
    java.util.Optional<JobUrl> findByIdWithJob(Long id);
}
