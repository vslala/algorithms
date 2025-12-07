package forwardretrypattern.exceptions;

import lombok.NoArgsConstructor;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 07/12/2025
 */
@NoArgsConstructor
public class CreatePolicyException extends RuntimeException {
    public CreatePolicyException(String msg) {
        super(msg);
    }
}
