package remitly.verify;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jsonpolicy.RolePolicy;
import jsonpolicy.Statement;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class RolePolicyVerifier {
    private RolePolicy policy = null;

    /**
     * Constructor for RolePolicyVerifier, deserializes the policy JSON AWS::IAM::Role Policy file
     * @param pathToPolicyFile - path to the JSON AWS::IAM::Role Policy file
     * @throws FileNotFoundException - if it couldn't find the file in the specified path
     */
    public RolePolicyVerifier(String pathToPolicyFile) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader;
        reader = new JsonReader(new FileReader(pathToPolicyFile));
        policy = gson.fromJson(reader, RolePolicy.class);
    }

    /**
     * Validates the policy format (contains all required fields)
     * and checks if the policy resource field in each statement is set to "*"
     * @return true if resource field in every statement is different from "*", false otherwise
     * @throws IncorrectPolicyFormatException - if the policy file does not meet the format requirements
     */
    public boolean verifyPolicy() throws IncorrectPolicyFormatException {
        validatePolicyFormat();

        return policy.PolicyDocument().Statement().stream().noneMatch(statement -> statement.Resource().equals("*"));
    }

    private void validatePolicyFormat() throws IncorrectPolicyFormatException {
        if (policy == null) {
            throw new IncorrectPolicyFormatException("Policy is not set");
        } else if (policy.PolicyName() == null) {
            throw new IncorrectPolicyFormatException("PolicyName is not set");
        } else if (policy.PolicyDocument() == null) {
            throw new IncorrectPolicyFormatException("PolicyDocument is not set");
        } else if (policy.PolicyDocument().Statement() == null) {
            throw new IncorrectPolicyFormatException("PolicyDocument's Statement is not set");
        } else if (policy.PolicyDocument().Version() == null) {
            throw new IncorrectPolicyFormatException("PolicyDocument's Version is not set");
        }
        for (Statement statement : policy.PolicyDocument().Statement()) {
            if (statement.Effect() == null) {
                throw new IncorrectPolicyFormatException("Statement's Effect is not set");
            } else if (statement.Action() == null) {
                throw new IncorrectPolicyFormatException("Statement's Action is not set");
            } else if (statement.Resource() == null) {
                throw new IncorrectPolicyFormatException("Statement's Resource is not set");
            }
        }

        if (policy.PolicyName().length() < 1 || policy.PolicyName().length() > 128 || !policy.PolicyName().matches("[\\w+=,.@-]+")) {
            throw new IncorrectPolicyFormatException("PolicyName is not valid");
        }
    }

    /**
     * Getter for the RolePolicy object
     * @return RolePolicy object
     * @throws IncorrectPolicyFormatException - if the policy file does not meet the format requirements
     */
    public RolePolicy getPolicy() throws IncorrectPolicyFormatException {
        validatePolicyFormat();

        return policy;
    }
}
