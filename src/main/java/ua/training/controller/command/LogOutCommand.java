package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Attribute;
import ua.training.model.enumeration.UserRole;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.Path.REDIRECT_TO_LOG_IN;

public class LogOutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing LogOutCommand");
        CommandUtility.setSession(request.getSession(), UserRole.GUEST, Attribute.SESSION_ROLE);
        CommandUtility.removeFromLoggedUsers(request.getSession(), (String) request.getSession().getAttribute(Attribute.SESSION_EMAIL));
        return REDIRECT_TO_LOG_IN;
    }
}
