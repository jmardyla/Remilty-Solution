import remilty.verify.IncorrectPolicyFormatException;
import remilty.verify.RolePolicyVerifier;

import java.io.FileNotFoundException;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("[WARNING] Usage: java -jar ./remilty-internship-1.0.jar <path_to_policy_file>");
            exit(1);
        }
        String path = args[0];
        RolePolicyVerifier verifier = null;
        System.out.println("[INFO] Verifying policy at " + path);
        try {
            verifier = new RolePolicyVerifier(path);
        } catch (FileNotFoundException e) {
            System.err.println("[ERR] " + e.getMessage());
            exit(1);
        }

        try {
            System.out.println("[INFO] Verifier result: " + verifier.verifyPolicy());
        } catch (IncorrectPolicyFormatException e) {
            System.err.println("[ERR] " + e.getMessage());
            exit(1);
        }
    }
}
