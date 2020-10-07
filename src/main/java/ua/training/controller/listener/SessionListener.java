package ua.training.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.command.CommandUtility;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(CommandUtility.class);

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        CommandUtility.removeFromLoggedUsers(se.getSession(), (String) se.getSession().getAttribute("userName"));
        logger.debug("user deleted: " + se.getSession().getAttribute("userName"));
    }
}
