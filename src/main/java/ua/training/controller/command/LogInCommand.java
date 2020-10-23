package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.exception.db.UserNotExistsException;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.Encryptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.controller.Attribute.*;
import static ua.training.controller.FieldConst.*;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECTS;
import static ua.training.controller.Path.REDIRECT_TO_LOG_IN;
import static ua.training.exception.Message.USER_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;

public class LogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogInCommand.class);

    private UserService userService = new UserService();

    private boolean validate(String email, String pass) {
        return email == null || pass == null || !email.matches(REGEX_EMAIL)
                || pass.length() < MIN_PASS_LENGTH || pass.length() > MAX_PASS_LENGTH;
    }

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing LogInCommand");

        HttpSession session = req.getSession();

        String email = req.getParameter(REQUEST_EMAIL);
        String pass = req.getParameter(REQUEST_PASS);
        CommandUtility.setSession(session, email, TRIED_USER_LOGIN);

        if (validate(email, pass)) {
            logger.info(VALIDATION_FAILED);
            CommandUtility.setSession(session, MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_LOG_IN;
        }

        User user;
        try {
            user = userService.loginByEmailAndPass(email, Encryptor.cryptWithMD5(pass));
        } catch (UserNotExistsException e) {
            logger.error(USER_SERVICE_ERROR, e);
            CommandUtility.setSession(session, e.getMessage(), SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_LOG_IN;
        } catch (DBException e) {
            logger.error(USER_SERVICE_ERROR, e);
            CommandUtility.setSession(session, MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_LOG_IN;
        }

        if (CommandUtility.isUserLogged(session, user.getEmail())) {
            logger.info("User is already logged");
            CommandUtility.setSession(session, MESSAGE_ACTION_FORM_ALREADY_LOGGED, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_LOG_IN;
        }

        CommandUtility.setSession(session, user.getRole(), SESSION_ROLE);
        CommandUtility.setSession(session, user.getEmail(), SESSION_EMAIL);
        CommandUtility.setSession(session, user.getId(), SESSION_USER_ID);

        return REDIRECTS + user.getRole().getRedirect();
    }
}
