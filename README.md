## Remitly Internship Assignment Solution - Jan Mardyła, Kraków, 2024

This repository includes both the project with all the source code and tests code, as well as an executable fat-jar file, sufficient for running the program.

---

### Prerequisites
- [Java version 17 or higher](https://www.oracle.com/pl/java/technologies/downloads/) 

### How to run
1. Download the [jar](https://github.com/jmardyla/Remitly-Solution/blob/main/remitly-verifier-1.0.jar) from the repository or clone the whole repository
2. Navigate to the location of the .jar file
3. ```bash
   java -jar ./remitly-verifier-1.0.jar <path-to-JSON-policy-file>
   ```
#### Example
![Example Usage Screenshot](/resources/usage-example.png)

   
### Alternatively
You can use the jar file as a Java library. Add it to your project and then you can use it like this:
```java
import remitly.verify.IncorrectPolicyFormatException;
import remitly.verify.RolePolicyVerifier;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        RolePolicyVerifier verifier = null;
        try {
            verifier = new RolePolicyVerifier("src/policy.json");

            boolean result = verifier.verifyPolicy();
            System.out.println("Policy verification result: " + result);
        } catch (FileNotFoundException | IncorrectPolicyFormatException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
```

### Tech Stack
- Java Oracle OpenJDK version 17
- [Gson](https://github.com/google/gson) 2.10.1
- JUnit 5.9.0
- Maven 3
