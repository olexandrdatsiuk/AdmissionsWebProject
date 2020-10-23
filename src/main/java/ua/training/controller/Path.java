package ua.training.controller;

import ua.training.exception.Message;

/**
 * Path class contains constants paths.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class Path {
    private Path() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static final String DIR_USER = "user/";
    public static final String DIR_ADMIN = "admin/";

    public static final String INDEX = "index.jsp";
    public static final String LOG_IN = "login.jsp";
    public static final String SIGN_UP = "signup.jsp";
    public static final String BASIC = "basic.jsp";
    public static final String APPLY = "apply.jsp";
    public static final String ADD_SUBJECT = "add_subject.jsp";
    public static final String SUBJECTS = "subjects.jsp";
    public static final String APPLIES = "applies.jsp";
    public static final String BLOCK = "block.jsp";
    public static final String UNBLOCK = "unblock.jsp";
    public static final String MANAGE_UNIVERSITY = "manage_university.jsp";
    public static final String REQUESTS = "requests.jsp";
    public static final String FINALIZE = "finalize.jsp";
    public static final String ADD_FACULTY = "add_faculty.jsp";
    public static final String UPDATE_FACULTY = "update_faculty.jsp";
    public static final String DELETE_FACULTY = "delete_faculty.jsp";
    public static final String USER_BASIC_PATH = DIR_USER + BASIC;
    public static final String ADMIN_BASIC_PATH = DIR_ADMIN + BASIC;

    public static final String CONTEXT = "/AdmissionsWebProject";
    public static final String WEB_INF = "/WEB-INF/";
    public static final String RESOURCE = "resource/";
    public static final String REDIRECTS = "redirect:/";
    public static final String REDIRECT = "redirect:";
    public static final String REDIRECT_TO_LOG_IN = REDIRECTS + LOG_IN;
    public static final String REDIRECT_TO_SIGN_UP = REDIRECTS + SIGN_UP;
    public static final String REDIRECT_TO_ADD_SUBJECT = REDIRECTS + DIR_USER + ADD_SUBJECT;
    public static final String REDIRECT_TO_BLOCK = REDIRECTS + DIR_ADMIN + BLOCK;
    public static final String REDIRECT_TO_UNBLOCK = REDIRECTS + DIR_ADMIN + UNBLOCK;
    public static final String REDIRECT_TO_FINALIZE = REDIRECTS + DIR_ADMIN + FINALIZE;
    public static final String FORWARD_TO_ADD_FACULTY = WEB_INF + DIR_ADMIN + ADD_FACULTY;
    public static final String FORWARD_TO_UPDATE_FACULTY = WEB_INF + DIR_ADMIN + UPDATE_FACULTY;
    public static final String FORWARD_TO_DELETE_FACULTY = WEB_INF + DIR_ADMIN + DELETE_FACULTY;
    public static final String REDIRECT_TO_MANAGE_UNIVERSITY = REDIRECTS + DIR_ADMIN + MANAGE_UNIVERSITY;
    public static final String REDIRECT_TO_REQUESTS = REDIRECTS + DIR_ADMIN + REQUESTS;
    public static final String REDIRECT_TO_APPLY = REDIRECTS + DIR_USER + APPLY;
}

