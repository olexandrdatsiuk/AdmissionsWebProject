package ua.training.controller;

import ua.training.exception.Message;

public class AppConst {
    private AppConst() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static final String REGEX_REMOVE_APP_DIRECTORY = "^.*?/AdmissionsWebProject/";
    public static final String REGEX_RESOURCE_FILE_EXTENSION = "(.css|.jpg|.js)$";
    public static final String REGEX_REMOVE_UP_TO_FILE_OR_COMMAND = ".*/AdmissionsWebProject/(user|admin|)(/|)";
    public static final String PATH_TO_RESOURCE_DIRECTORY = "C:\\Mine\\EC\\AdmissionsWebProject\\src\\main\\webapp\\";

}
