package ua.training.controller;

import static ua.training.exception.Message.PRIVATE_CONSTRUCTOR_ERROR;

public class Message {
    private Message() {
        throw new AssertionError(PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static final String MESSAGE_ACTION_APP_EXCEPTION_ERROR = "message.action.app.exception.error";
    public static final String MESSAGE_ACTION_FORM_INCORRECT = "message.action.form.incorrect";
    public static final String MESSAGE_ACTION_FORM_ALREADY_LOGGED = "message.action.form.already.logged";
    public static final String MESSAGE_ACTION_FORM_INCORRECT_LOGIN_DATA = "message.action.form.incorrect.login.data";
    public static final String MESSAGE_ACTION_FORM_BLOCKED_USER = "message.action.form.blocked.user";
    public static final String MESSAGE_ACTION_FORM_CHOOSE_EMAIL = "message.action.form.choose.email";
    public static final String MESSAGE_ACTION_FORM_SIGNUP_SUCCESSFUL = "message.action.form.signup.successful";
    public static final String MESSAGE_ACTION_SUBJECT_ADDED = "message.action.subject.added";
    public static final String MESSAGE_ACTION_USER_NOT_FOUND = "message.action.user.not.found";
    public static final String MESSAGE_ACTION_USER_BLOCKED = "message.action.user.blocked";
    public static final String MESSAGE_ACTION_USER_UNBLOCKED = "message.action.user.unblocked";
    public static final String MESSAGE_ACTION_FACULTY_FINALIZED = "message.action.faculty.finalized";
    public static final String MESSAGE_ACTION_FACULTY_DELETED = "message.action.faculty.deleted";
    public static final String MESSAGE_ACTION_FACULTY_NOT_FOUND = "message.action.faculty.not.found";
    public static final String MESSAGE_ACTION_FACULTY_UPDATED = "message.action.faculty.updated";
    public static final String MESSAGE_ACTION_FACULTY_ADDED = "message.action.faculty.added";
    public static final String MESSAGE_ACTION_REQUEST_CHANGED = "message.action.request.changed";
    public static final String MESSAGE_ACTION_REQUEST_APPLIED = "message.action.request.applied";

}
