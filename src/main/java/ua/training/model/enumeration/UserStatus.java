package ua.training.model.enumeration;

/**
 * Represents an enumeration of statuses of users.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public enum UserStatus {
    BLOCKED(1),
    UNBLOCKED(2);

    private int status;

    UserStatus(int s) {
        status = s;
    }

    public int getStatus() {
        return status;
    }
}