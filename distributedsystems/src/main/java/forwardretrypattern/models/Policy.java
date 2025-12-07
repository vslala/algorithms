package forwardretrypattern.models;

import lombok.With;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 07/12/2025
 */
public record Policy(String id, @With String externalID, @With Status status, String description, String statement) {
}
