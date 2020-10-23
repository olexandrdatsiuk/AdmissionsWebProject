package ua.training.controller;

import ua.training.exception.Message;

/**
 * FieldConst class contains constants used to validate data
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class FieldConst {
    private FieldConst() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static final int MAX_REQUESTS_ON_PAGE = 3;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-z0-9]+\\.[a-z]+";
    public static final int MIN_PASS_LENGTH = 8;
    public static final int MAX_PASS_LENGTH = 20;
    public static final int PARSE_NUMBER_FAILED = 0;
    public static final int MIN_AVERAGE_SCORE = 1;
    public static final int MAX_AVERAGE_SCORE = 12;
    public static final int MIN_FIRST_NAME_LENGTH = 5;
    public static final int MIN_LAST_NAME_LENGTH = 5;
    public static final int MIN_MIDDLE_NAME_LENGTH = 5;
    public static final int MIN_CITY_LENGTH = 5;
    public static final int MIN_REGION_LENGTH = 5;
    public static final int MIN_SUBJECT_SCORE = 100;
    public static final int MAX_SUBJECT_SCORE = 200;
    public static final int MIN_FREE_AMOUNT = 0;
    public static final int MIN_ALL_AMOUNT = 1;

}
