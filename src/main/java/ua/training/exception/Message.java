package ua.training.exception;

public class Message {
    private Message() {
        throw new AssertionError(PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static final String PRIVATE_CONSTRUCTOR_ERROR = "This constructor is not for you!";
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String FACULTY_SERVICE_ERROR = "Faculty Service error";
    public static final String SUBJECT_SERVICE_ERROR = "Subject Service error";
    public static final String REQUEST_SERVICE_ERROR = "Request Service error";
    public static final String USER_SERVICE_ERROR = "User Service error";
    public static final String DAO_EXCEPTION_THROWN = "DAO exception thrown";
    public static final String SQL_HANDLER_TAG_EXCEPTION_THROWN = "Sql handler tag exception thrown";
}
