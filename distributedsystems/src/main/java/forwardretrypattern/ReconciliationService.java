package forwardretrypattern;

import com.github.benmanes.caffeine.cache.Cache;
import forwardretrypattern.exceptions.CreatePolicyException;
import forwardretrypattern.models.Policy;
import forwardretrypattern.models.Status;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 07/12/2025
 */
class ReconciliationService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Cache<String, Policy> db;
    private final ExternalService externalService;

    public ReconciliationService(Cache<String, Policy> db, ExternalService externalService) {
        this.db = db;
        this.externalService = externalService;
    }

    public void scheduleReconciliation(String policyId) {
        scheduler.schedule(() -> reconcilePolicy(policyId), 2, TimeUnit.SECONDS);
    }

    private void reconcilePolicy(String policyId) {
        Policy policy = db.getIfPresent(policyId);
        if (policy == null) {
            return;
        }

        switch (policy.status()) {
            case CREATE_PENDING:
                reconcileCreatePending(policy);
                break;
            case FAILED, ACTIVE:
                break;
        }
    }

    private void reconcileCreatePending(Policy policy) {
        try {
            String externalId = externalService.createPolicy(policy);
            Policy updatedPolicy = policy.withExternalID(externalId).withStatus(Status.ACTIVE);
            db.put(policy.id(), updatedPolicy);
        } catch (CreatePolicyException ex) {
            Policy failedPolicy = policy.withStatus(Status.FAILED);
            db.put(policy.id(), failedPolicy);
        }
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
