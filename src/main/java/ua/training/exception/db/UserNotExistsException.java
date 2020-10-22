package ua.training.exception.db;

public class UserNotExistsException extends DBException {
    public UserNotExistsException() {
        super();
    }

    public UserNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistsException(String message) {
        super(message);
    }

    public UserNotExistsException(Throwable cause) {
        super(cause);
    }
}
