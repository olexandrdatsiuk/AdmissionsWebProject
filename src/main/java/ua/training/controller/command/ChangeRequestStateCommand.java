package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.model.enumeration.RequestState;
import ua.training.model.service.RequestService;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.controller.Attribute.*;
import static ua.training.controller.FieldConst.PARSE_NUMBER_FAILED;
import static ua.training.controller.Message.MESSAGE_ACTION_APP_EXCEPTION_ERROR;
import static ua.training.controller.Message.MESSAGE_ACTION_REQUEST_CHANGED;
import static ua.training.controller.Path.REDIRECT_TO_REQUESTS;
import static ua.training.exception.Message.REQUEST_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class ChangeRequestStateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeRequestStateCommand.class);

    private RequestService requestService = new RequestService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing ChangeRequestStateCommand");
        HttpSession session = req.getSession();
        int userId;
        int facultyId;
        int state;
        if (
                (userId = NumericParser.parseInt(req.getParameter(REQUEST_USER_ID))) == PARSE_NUMBER_FAILED
                        || (facultyId = NumericParser.parseInt(req.getParameter(REQUEST_FACULTY_ID))) == PARSE_NUMBER_FAILED
                        || (state = NumericParser.parseInt(req.getParameter(REQUEST_STATE))) == PARSE_NUMBER_FAILED
                        || state < RequestState.REJECTED.getState() || state > RequestState.ACCEPTED.getState()

        ) {
            logger.info(VALIDATION_FAILED);
            CommandUtility.setSession(session, MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_REQUESTS;
        }
        try {
            requestService.changeRequestStatus(userId, facultyId, state);
            CommandUtility.setSession(session, MESSAGE_ACTION_REQUEST_CHANGED, SESSION_ERROR_MESSAGE_ACTIVITY);
        } catch (DBException e) {
            logger.error(REQUEST_SERVICE_ERROR, e);
            CommandUtility.setSession(session, MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
        }
        return REDIRECT_TO_REQUESTS;
    }
}
