package ua.training.controller;

import ua.training.exception.Message;

public class Attribute {
    private Attribute() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static final String CONTEXT_LOGGED_USERS = "loggedUsers";
    public static final String CONTEXT_REQUEST_STATES = "request_states";
    public static final String CONTEXT_FACULTY_COMPARATOR = "faculty_comparator";
    public static final String CONTEXT_UNIVERSITY_ACTION = "university_action";

    public static final String REQUEST_LANG = "lang";
    public static final String REQUEST_EMAIL = "email";
    public static final String REQUEST_PASS = "pass";
    public static final String REQUEST_FIRST_NAME = "first_name";
    public static final String REQUEST_LAST_NAME = "last_name";
    public static final String REQUEST_MIDDLE_NAME = "middle_name";
    public static final String REQUEST_CITY = "city";
    public static final String REQUEST_STATE = "state";
    public static final String REQUEST_REGION = "region";
    public static final String REQUEST_UNIVERSITY = "university";
    public static final String REQUEST_FACULTY = "faculty";
    public static final String REQUEST_FACULTIES = "faculties";
    public static final String REQUEST_FACULTY_ID = "facultyId";
    public static final String REQUEST_USER_ID = "userId";
    public static final String REQUEST_AVERAGE_SCORE = "average_score";
    public static final String REQUEST_FREE_AMOUNT = "free_amount";
    public static final String REQUEST_ALL_AMOUNT = "all_amount";
    public static final String REQUEST_SUBJECT = "subject";
    public static final String REQUEST_SCORE = "score";
    public static final String REQUEST_ACTION = "action";

    public static final String SESSION_LANG = "lang";
    public static final String SESSION_ROLE = "role";
    public static final String SESSION_EMAIL = "email";
    public static final String SESSION_ERROR_MESSAGE_ACTIVITY = "info_message_activity";
    public static final String SESSION_USER_ID = "user_id";
    public static final String TRIED_USER_LOGIN = "tried_login_user";
    public static final String TRIED_USER_SIGNUP = "tried_signup_user_data";

}
