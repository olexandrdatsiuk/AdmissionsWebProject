package ua.training.model.sql;

import ua.training.exception.Message;

import java.util.ResourceBundle;
/**
 * JDBCQuery class contains constants of jdbc queries.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class JDBCQuery {
    private static ResourceBundle rb = ResourceBundle.getBundle("db-queries");

    private JDBCQuery() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static String FIND_ALL_UNIVERSITIES = rb.getString("university.find.all");

    public static String SUBJECTS_FOR_USER_NOT_HAVE = rb.getString("subject.find.for.user.not.have");
    public static String SET_SUBJECT_FOR_USER = rb.getString("subject.set.for.user");
    public static String SUBJECTS_NAMES = rb.getString("subject.find.all.names");
    public static String FIND_SUBJECTS_NAME_SCORE_FOR_USER = rb.getString("subject.find.for.user");

    public static String INSERT_INTO_STUDY_ACCOUNT = rb.getString("user.insert.study.account");
    public static String INSERT_INTO_ACCOUNT = rb.getString("user.insert.account");
    public static String INSERT_INTO_PERSON = rb.getString("user.insert.person");
    public static String FIND_STUDY_ACCOUNT_DETAILS = rb.getString("user.find.study.account.details");
    public static String LOGIN_BY_EMAIL_PASSWORD = rb.getString("user.find.by.email.password");
    public static String FIND_ACCOUNT_DETAILS = rb.getString("user.find.account.details");
    public static String BLOCK_USER_BY_EMAIL = rb.getString("user.update.block.by.email");

    public static String UPDATE_REQUEST_STATE = rb.getString("request.update.change.state");
    public static String FIND_ALL_REQUESTS = rb.getString("request.find.all");
    public static String FIND_REQUESTS_FOR_USER = rb.getString("request.find.for.user");
    public static String CREATE_REQUEST = rb.getString("request.create");

    public static String FINALIZE_FACULTY = rb.getString("faculty.update.finalize");
    public static String FIND_ALL_FACULTIES = rb.getString("faculty.find.all");
    public static String FIND_FACULTIES_FOR_USER_BY_UNIVERSITY = rb.getString("faculty.find.faculties.for.user.by.university");
    public static String FIND_SUBJECTS_ID_FOR_USER = rb.getString("faculty.find.user.subjects");
    public static String UPDATE_REQUESTS_WHEN_DELETE_FROM_UNIVERSITY = rb.getString("faculty.update.requests.when.delete.from.university");
    public static String FACULTIES_FOR_UNIVERSITY_NOT_HAVE = rb.getString("faculty.find.for.university.not.have");
    public static String INSERT_INTO_UNIVERSITY_HAS_FACULTY = rb.getString("faculty.set.faculty.for.university");
    public static String DELETE_FACULTY_FOR_UNIVERSITY = rb.getString("faculty.delete.faculty.for.university");
    public static String UPDATE_FACULTY_FOR_UNIVERSITY = rb.getString("faculty.update.faculty.for.university");
    public static String FACULTIES_NAMES_FOR_UNIVERSITY = rb.getString("faculty.find.names.for.university");
    public static String FACULTIES_FOR_UNIVERSITY = rb.getString("faculty.find.all.for.university");
}
