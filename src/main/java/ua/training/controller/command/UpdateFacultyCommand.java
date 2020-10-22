package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.exception.db.FacultyNotExistsException;
import ua.training.model.entity.Faculty;
import ua.training.model.service.FacultyService;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.Attribute.*;
import static ua.training.controller.FieldConst.*;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECT_TO_MANAGE_UNIVERSITY;
import static ua.training.exception.Message.REQUEST_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class UpdateFacultyCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateFacultyCommand.class);

    private FacultyService facultyService = new FacultyService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing UpdateFacultyCommand");

        int universityId;
        int facultyId;
        int freeAmount;
        int allAmount;
        if ((universityId = NumericParser.parseInt(req.getParameter(REQUEST_UNIVERSITY))) == PARSE_NUMBER_FAILED
                || (facultyId = NumericParser.parseInt(req.getParameter(REQUEST_FACULTY))) == PARSE_NUMBER_FAILED
                || (freeAmount = NumericParser.parseInt(req.getParameter(REQUEST_FREE_AMOUNT))) == PARSE_NUMBER_FAILED
                || (allAmount = NumericParser.parseInt(req.getParameter(REQUEST_ALL_AMOUNT))) == PARSE_NUMBER_FAILED
                || freeAmount < MIN_FREE_AMOUNT
                || allAmount < MIN_ALL_AMOUNT
                || freeAmount > allAmount) {
            logger.info(VALIDATION_FAILED);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_MANAGE_UNIVERSITY;
        }
        Faculty faculty = new Faculty.FacultyBuilder()
                .setId(facultyId)
                .setFreeAmount(freeAmount)
                .setAllAmount(allAmount)
                .build();
        try {
            facultyService.updateFacultyForUniversity(universityId, faculty);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FACULTY_UPDATED, SESSION_ERROR_MESSAGE_ACTIVITY);
        } catch (FacultyNotExistsException e) {
            logger.error(REQUEST_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), e.getMessage(), SESSION_ERROR_MESSAGE_ACTIVITY);
        } catch (DBException e) {
            logger.error(REQUEST_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
        }
        return REDIRECT_TO_MANAGE_UNIVERSITY;
    }
}
