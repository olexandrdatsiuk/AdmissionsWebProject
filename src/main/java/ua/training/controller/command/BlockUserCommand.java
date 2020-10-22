package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Attribute;
import ua.training.exception.db.DBException;
import ua.training.exception.db.UserNotExistsException;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.Attribute.SESSION_ERROR_MESSAGE_ACTIVITY;
import static ua.training.controller.FieldConst.REGEX_EMAIL;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECT_TO_BLOCK;
import static ua.training.exception.Message.USER_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class BlockUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing BlockUserCommand");

        String email = req.getParameter(Attribute.REQUEST_EMAIL);
        if (email == null || !email.matches(REGEX_EMAIL)) {
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            logger.info(VALIDATION_FAILED);
            return REDIRECT_TO_BLOCK;
        }
        try {
            userService.changeStatus(email, User.Status.BLOCKED, (int) req.getSession().getAttribute(Attribute.SESSION_USER_ID));
        } catch (UserNotExistsException e) {
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_USER_NOT_FOUND, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_BLOCK;
        } catch (DBException e) {
            logger.error(USER_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_BLOCK;
        }
        CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_USER_BLOCKED, SESSION_ERROR_MESSAGE_ACTIVITY);
        return REDIRECT_TO_BLOCK;
    }
}
