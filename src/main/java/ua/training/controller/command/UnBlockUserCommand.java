package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Attribute;
import ua.training.exception.db.DBException;
import ua.training.exception.db.UserNotExistsException;
import ua.training.model.enumeration.UserStatus;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.Attribute.SESSION_ERROR_MESSAGE_ACTIVITY;
import static ua.training.controller.FieldConst.REGEX_EMAIL;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECT_TO_UNBLOCK;
import static ua.training.exception.Message.REQUEST_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class UnBlockUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UnBlockUserCommand.class);

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing UnBlockUserCommand");

        String email = req.getParameter(Attribute.REQUEST_EMAIL);

        if (email == null || !email.matches(REGEX_EMAIL)) {
            logger.info(VALIDATION_FAILED);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_UNBLOCK;
        }
        try {
            userService.changeStatus(email, UserStatus.UNBLOCKED, (int) req.getSession().getAttribute(Attribute.SESSION_USER_ID));
        } catch (UserNotExistsException e) {
            logger.error(REQUEST_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_USER_NOT_FOUND, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_UNBLOCK;
        } catch (DBException e) {
            logger.error(REQUEST_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_UNBLOCK;
        }
        CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_USER_UNBLOCKED, SESSION_ERROR_MESSAGE_ACTIVITY);
        return REDIRECT_TO_UNBLOCK;
    }
}
