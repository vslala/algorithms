package forwardretrypattern;

import com.github.benmanes.caffeine.cache.Cache;
import forwardretrypattern.exceptions.CreatePolicyException;
import forwardretrypattern.models.CreatePolicyRequest;
import forwardretrypattern.models.Policy;
import forwardretrypattern.models.Status;

import java.util.UUID;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 05/12/2025
 */
class PolicyService {

    private final ExternalService externalService;
    private final Cache<String, Policy> db;
    private final ReconciliationService reconciliationService;

    public PolicyService(Cache<String, Policy> db, ExternalService externalService, ReconciliationService reconciliationService) {
        this.externalService = externalService;
        this.db = db;
        this.reconciliationService = reconciliationService;
    }

    public Policy compensateActionsIfExternalServiceFailsToCreatePolicy(CreatePolicyRequest createPolicyRequest) {
        String policyId = UUID.randomUUID().toString();
        this.reconciliationService.scheduleReconciliation(policyId);
        var policy = new Policy(policyId, "", Status.CREATE_PENDING, createPolicyRequest.description(), createPolicyRequest.statement());
        db.put(policyId, policy);
        try {
            String externalId = this.externalService.createPolicy(policy);
            Policy createdPolicy = policy.withExternalID(externalId).withStatus(Status.ACTIVE);
            this.db.put(policyId, createdPolicy);
            return createdPolicy;
        } catch (CreatePolicyException ex) {
            this.db.put(policyId, policy.withStatus(Status.FAILED));
            throw new CreatePolicyException();
        }
    }

    public Policy crashAfterCallingExternalService(CreatePolicyRequest createPolicyRequest) {
        String policyId = UUID.randomUUID().toString();
        this.reconciliationService.scheduleReconciliation(policyId);
        var policy = new Policy(policyId, "", Status.CREATE_PENDING, createPolicyRequest.description(), createPolicyRequest.statement());
        db.put(policyId, policy);
        String externalId = this.externalService.createPolicy(policy);

        return null;
    }

    public Policy compensationActionWithReconciliation(CreatePolicyRequest createPolicyRequest) {
        String policyId = UUID.randomUUID().toString();
        this.reconciliationService.scheduleReconciliation(policyId);
        var policy = new Policy(policyId, "", Status.CREATE_PENDING, createPolicyRequest.description(), createPolicyRequest.statement());
        db.put(policyId, policy);

        String externalId = this.externalService.createPolicy(policy);


        return null;
    }
}
