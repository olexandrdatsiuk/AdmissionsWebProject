package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Attribute;
import ua.training.exception.db.DBException;
import ua.training.model.service.RequestService;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.controller.Attribute.*;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECT_TO_APPLY;
import static ua.training.exception.Message.REQUEST_SERVICE_ERROR;

public class ApplyCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ApplyCommand.class);
    private RequestService requestService = new RequestService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing ApplyCommand");
        HttpSession session = req.getSession();

        int facultyId;
        if ((facultyId = NumericParser.parseInt(req.getParameter(REQUEST_FACULTY))) == 0) {
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_APPLY;
        }
        try {
            requestService.create(
                    (int) session.getAttribute(Attribute.SESSION_USER_ID),
                    facultyId,
                    (String) session.getAttribute(Attribute.SESSION_LANG));
        } catch (DBException e) {
            logger.error(REQUEST_SERVICE_ERROR, e);
            CommandUtility.setSession(session, MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
        }
        CommandUtility.setSession(session, MESSAGE_ACTION_REQUEST_APPLIED, SESSION_ERROR_MESSAGE_ACTIVITY);
        return REDIRECT_TO_APPLY;
    }
}
