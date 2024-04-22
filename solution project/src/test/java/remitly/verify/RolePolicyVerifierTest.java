package remitly.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RolePolicyVerifierTest {
    @Test
    void rolePolicyVerifierConstructorCorrectPath() {
        Assertions.assertDoesNotThrow(() -> {
            RolePolicyVerifier verifier = new RolePolicyVerifier("src/test/resources/valid_policy.json");
        });
    }

    @Test
    void rolePolicyVerifierConstructorIncorrectPath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            RolePolicyVerifier verifier = new RolePolicyVerifier("incorrect_path");
        });
    }

    @Test
    void verifyPolicyValidPolicyExpectedFalse() {
        RolePolicyVerifier verifier = null;
        try {
            verifier = new RolePolicyVerifier("src/test/resources/valid_policy.json");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertFalse(verifier.verifyPolicy());
        } catch (IncorrectPolicyFormatException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void verifyPolicyValidPolicyExpectedTrue() {
        RolePolicyVerifier verifier = null;
        try {
            verifier = new RolePolicyVerifier("src/test/resources/valid_policy_wrong_resource.json");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertTrue(verifier.verifyPolicy());
        } catch (IncorrectPolicyFormatException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void verifyPolicyValidPolicyMultipleStatementsExpectedFalse() {
        RolePolicyVerifier verifier = null;
        try {
            verifier = new RolePolicyVerifier("src/test/resources/valid_policy_multiple_statements.json");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertFalse(verifier.verifyPolicy());
        } catch (IncorrectPolicyFormatException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void verifyPolicyInvalidPolicyExpectedThrows() {
        RolePolicyVerifier verifier = null;
        try {
            verifier = new RolePolicyVerifier("src/test/resources/invalid_policy_no_effect.json");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertThrows(IncorrectPolicyFormatException.class, verifier::verifyPolicy);

        RolePolicyVerifier verifier2 = null;
        try {
            verifier2 = new RolePolicyVerifier("src/test/resources/invalid_policy_incorrect_policy_name.json");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertThrows(IncorrectPolicyFormatException.class, verifier2::verifyPolicy);
    }
}