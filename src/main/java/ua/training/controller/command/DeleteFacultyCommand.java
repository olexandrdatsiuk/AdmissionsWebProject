package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.model.enumeration.RequestState;
import ua.training.model.service.FacultyService;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.training.controller.Attribute.SESSION_ERROR_MESSAGE_ACTIVITY;
import static ua.training.controller.FieldConst.PARSE_NUMBER_FAILED;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECT_TO_MANAGE_UNIVERSITY;
import static ua.training.exception.Message.FACULTY_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class DeleteFacultyCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteFacultyCommand.class);

    private FacultyService facultyService = new FacultyService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing DeleteFacultyCommand");

        int universityId;
        int facultyId;
        if ((universityId = NumericParser.parseInt(req.getParameter("university"))) == PARSE_NUMBER_FAILED
                || (facultyId = NumericParser.parseInt(req.getParameter("faculty"))) == PARSE_NUMBER_FAILED) {
            logger.info(VALIDATION_FAILED);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_MANAGE_UNIVERSITY;
        }
        try {
            facultyService.deleteFromUniversity(
                    universityId,
                    facultyId,
                    RequestState.REJECTED
            );
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FACULTY_DELETED, SESSION_ERROR_MESSAGE_ACTIVITY);
        } catch (DBException | SQLException e) {
            logger.error(FACULTY_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
        }
        CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FACULTY_DELETED, SESSION_ERROR_MESSAGE_ACTIVITY);
        return REDIRECT_TO_MANAGE_UNIVERSITY;
    }
}
