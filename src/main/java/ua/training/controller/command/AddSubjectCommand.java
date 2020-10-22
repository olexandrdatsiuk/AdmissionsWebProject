package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.model.entity.Subject;
import ua.training.model.service.SubjectService;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.Attribute.*;
import static ua.training.controller.FieldConst.*;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECT_TO_ADD_SUBJECT;
import static ua.training.exception.Message.SUBJECT_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class AddSubjectCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddSubjectCommand.class);
    private SubjectService subjectService = new SubjectService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.debug("Executing AddSubjectCommand");

        String idStr = req.getParameter(REQUEST_SUBJECT);
        String scoreStr = req.getParameter(REQUEST_SCORE);
        int id = NumericParser.parseInt(idStr);
        int score = NumericParser.parseInt(scoreStr);


        if (idStr == null || scoreStr == null
                || id == PARSE_NUMBER_FAILED
                || score < MIN_SUBJECT_SCORE || score > MAX_SUBJECT_SCORE
        ) {
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            logger.info(VALIDATION_FAILED);
            return REDIRECT_TO_ADD_SUBJECT;
        }

        CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_SUBJECT_ADDED, SESSION_ERROR_MESSAGE_ACTIVITY);
        Subject subject = new Subject((int) req.getSession().getAttribute(SESSION_USER_ID), id, score);
        try {
            subjectService.setSubjectForUser(subject);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_SUBJECT_ADDED, SESSION_ERROR_MESSAGE_ACTIVITY);
        } catch (DBException e) {
            logger.error(SUBJECT_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
        }

        return REDIRECT_TO_ADD_SUBJECT;
    }

}
