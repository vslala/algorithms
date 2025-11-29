package webcrawler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webcrawler.repositories.entities.JobEntity;

import java.util.Optional;

/**
 * Repository for Job entity
 *
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Repository
public interface JobRepository extends JpaRepository<JobEntity, String> {

    /**
     * Find job by job ID
     */
    Optional<JobEntity> findByJobId(String jobId);

    /**
     * Check if job exists by job ID
     */
    boolean existsByJobId(String jobId);

    /**
     * Count jobs by status
     */
    @Query("SELECT COUNT(j) FROM JobEntity j WHERE j.status = :status")
    long countByStatus(JobEntity.JobStatus status);

}
