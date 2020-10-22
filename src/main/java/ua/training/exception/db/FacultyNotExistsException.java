package ua.training.exception.db;

public class FacultyNotExistsException extends DBException {
    public FacultyNotExistsException() {
        super();
    }

    public FacultyNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FacultyNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacultyNotExistsException(String message) {
        super(message);
    }

    public FacultyNotExistsException(Throwable cause) {
        super(cause);
    }
}
