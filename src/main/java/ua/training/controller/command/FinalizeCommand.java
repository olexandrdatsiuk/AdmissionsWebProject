package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.model.service.FacultyService;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.Attribute.REQUEST_FACULTY;
import static ua.training.controller.Attribute.SESSION_ERROR_MESSAGE_ACTIVITY;
import static ua.training.controller.Message.MESSAGE_ACTION_APP_EXCEPTION_ERROR;
import static ua.training.controller.Message.MESSAGE_ACTION_FACULTY_FINALIZED;
import static ua.training.controller.Path.REDIRECT_TO_FINALIZE;
import static ua.training.exception.Message.FACULTY_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class FinalizeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FinalizeCommand.class);

    private FacultyService facultyService = new FacultyService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing FinalizeCommand");

        int id;
        if ((id = NumericParser.parseInt(req.getParameter(REQUEST_FACULTY))) == 0) {
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
            logger.info(VALIDATION_FAILED);
            return REDIRECT_TO_FINALIZE;
        }
        try {
            facultyService.finalizeFaculty(id);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FACULTY_FINALIZED, SESSION_ERROR_MESSAGE_ACTIVITY);
        } catch (DBException e) {
            logger.error(FACULTY_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
        }
        return REDIRECT_TO_FINALIZE;
    }
}
