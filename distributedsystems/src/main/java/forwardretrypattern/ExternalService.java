package forwardretrypattern;

import com.github.benmanes.caffeine.cache.Cache;
import forwardretrypattern.exceptions.CreatePolicyException;
import forwardretrypattern.models.CreatePolicyRequest;
import forwardretrypattern.models.Policy;
import forwardretrypattern.models.Status;

import java.util.Map;
import java.util.UUID;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 07/12/2025
 */
class ExternalService {

    private final Cache<String, Policy> db;
    private final Map<String, Boolean> scenarios;

    public ExternalService(Cache<String, Policy> db, Map<String, Boolean> scenarios) {
        this.db = db;
        this.scenarios = scenarios;
    }

    public String createPolicy(Policy policy) {
        String externalId = UUID.randomUUID().toString();
        if (scenarios.getOrDefault("CREATE_POLICY", false)) {
            this.db.put(policy.id(), policy.withStatus(Status.ACTIVE).withExternalID(externalId));
            return externalId;
        }

        if (scenarios.getOrDefault("THROW_EXCEPTION", false)) {
            throw new CreatePolicyException("500 - INTERNAL SERVER ERROR!");
        }

        return "";
    }

    public boolean deletePolicy(CreatePolicyRequest createPolicyRequest) {
        return true;
    }
}
