package forwardretrypattern;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import forwardretrypattern.exceptions.CreatePolicyException;
import forwardretrypattern.models.CreatePolicyRequest;
import forwardretrypattern.models.Policy;
import forwardretrypattern.models.Status;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 05/12/2025
 */
class PolicyServiceTest {

    private Cache<String, Policy> policyServiceDb;
    private Cache<String, Policy> externalServiceDb;

    @BeforeEach
    void setUp() {
        policyServiceDb = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .maximumSize(1000)
                .build();

        externalServiceDb = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .maximumSize(1000)
                .build();
    }

    @AfterEach
    void tearDown() {
        policyServiceDb.invalidateAll();
        externalServiceDb.invalidateAll();
    }




    @SneakyThrows
    @Test
    void it_should_create_policy_by_calling_external_service_and_update_state_in_its_table() {
        var externalService = new ExternalService(externalServiceDb, Map.of("CREATE_POLICY", true));
        var reconciliationService = new ReconciliationService(policyServiceDb, externalService);
        var policyService = new PolicyService(policyServiceDb, externalService, reconciliationService);

        CreatePolicyRequest testCreatePolicyRequest = new CreatePolicyRequest("This is a test policy", "permit(principal, action, resource);");
        Policy output = policyService.compensateActionsIfExternalServiceFailsToCreatePolicy(testCreatePolicyRequest);

        awaitSchedulerExecution();
        assertEquals(output.description(), testCreatePolicyRequest.description());
        assertEquals(output.statement(), testCreatePolicyRequest.statement());
        assertFalse(output.externalID().isBlank());
        assertFalse(output.id().isBlank());
        assertEquals(1, policyServiceDb.estimatedSize());
    }

    @SneakyThrows
    @Test
    void it_should_update_the_record_as_failed_in_db_if_external_service_fails() {
        var externalService = new ExternalService(externalServiceDb, Map.of("THROW_EXCEPTION", true));
        var reconciliationService = new ReconciliationService(policyServiceDb, externalService);
        var policyService = Mockito.spy(new PolicyService(policyServiceDb, externalService, reconciliationService));

        CreatePolicyRequest testCreatePolicyRequest = new CreatePolicyRequest("This is a test policy", "permit(principal, action, resource);");

        assertThrows(CreatePolicyException.class, () -> {
            Policy output = policyService.compensateActionsIfExternalServiceFailsToCreatePolicy(testCreatePolicyRequest);
        });
        awaitSchedulerExecution();

        assertEquals(1, policyServiceDb.estimatedSize());


        var keys = policyServiceDb.asMap().keySet();
        assertFalse(keys.isEmpty());
        String key = keys.stream().findFirst().orElseThrow();
        Policy failedPolicy = policyServiceDb.asMap().get(key);
        assertEquals(Status.FAILED, failedPolicy.status());
    }

    @SneakyThrows
    @Test
    void it_should_mimic_server_crash_when_the_policy_has_been_created_successfully_in_external_service() {
        var externalService = new ExternalService(externalServiceDb, Map.of("CREATE_POLICY", true));
        var reconciliationService = new ReconciliationService(policyServiceDb, externalService);
        var policyService = new PolicyService(policyServiceDb, externalService, reconciliationService);

        CreatePolicyRequest testCreatePolicyRequest = new CreatePolicyRequest("This is a test policy", "permit(principal, action, resource);");
        Policy output = policyService.crashAfterCallingExternalService(testCreatePolicyRequest);

        assertNull(output);
        assertEquals(1, policyServiceDb.estimatedSize());
        assertEquals(1, externalServiceDb.estimatedSize());
        // verify the policy service crashed with 'CREATE_PENDING' state
        var keys = policyServiceDb.asMap().keySet();
        assertFalse(keys.isEmpty());
        String key = keys.stream().findFirst().orElseThrow();
        Policy failedPolicy = policyServiceDb.asMap().get(key);
        assertEquals(Status.CREATE_PENDING, failedPolicy.status());
        assertTrue(failedPolicy.externalID().isEmpty());
        // verify that policy was created by the external service successfully, thus, inconsistent state
        Policy createdPolicy = externalServiceDb.asMap().get(failedPolicy.id());
        assertFalse(createdPolicy.externalID().isBlank());
        assertEquals(Status.ACTIVE, createdPolicy.status());

        // verify that the reconciliation service is working properly to reconcile the state
        awaitSchedulerExecution();
        assertEquals(Status.ACTIVE, policyServiceDb.asMap().get(failedPolicy.id()).status());
    }

    @Test
    void it_should_trigger_external_reconciliation_service_that_will_make_sure_the_database_state_is_correct() throws InterruptedException {
        var externalService = new ExternalService(externalServiceDb, Map.of("CREATE_POLICY", true));
        var reconciliationService = new ReconciliationService(policyServiceDb, externalService);
        var policyService = new PolicyService(policyServiceDb, externalService, reconciliationService);

        CreatePolicyRequest testCreatePolicyRequest = new CreatePolicyRequest("This is a test policy", "permit(principal, action, resource);");
        Policy output = policyService.compensationActionWithReconciliation(testCreatePolicyRequest);

        assertNull(output);
        assertEquals(1, policyServiceDb.estimatedSize());

        // verify the policy service crashed with 'CREATE_PENDING' state
        var keys = policyServiceDb.asMap().keySet();
        assertFalse(keys.isEmpty());
        String key = keys.stream().findFirst().orElseThrow();
        Policy failedPolicy = policyServiceDb.asMap().get(key);
        assertEquals(Status.CREATE_PENDING, failedPolicy.status());
        assertTrue(failedPolicy.externalID().isEmpty());

        // verify that policy was created by the external service successfully, thus, inconsistent state
        Policy createdPolicy = externalServiceDb.asMap().get(failedPolicy.id());
        assertFalse(createdPolicy.externalID().isBlank());
        assertEquals(Status.ACTIVE, createdPolicy.status());

        // Wait for reconciliation to complete (5 seconds delay + processing time)
        awaitSchedulerExecution();

        // Verify that the reconciliation service fixed the state
        Policy reconciledPolicy = policyServiceDb.asMap().get(key);
        assertFalse(reconciledPolicy.externalID().isEmpty());
        assertEquals(Status.ACTIVE, reconciledPolicy.status());

        reconciliationService.shutdown();
    }

    private static void awaitSchedulerExecution() throws InterruptedException {
        for (int i = 0; i < 5; i ++) {
            System.out.println("Waiting for reconciliation scheduler to execute...");
            Thread.sleep(500);
        }
    }

}