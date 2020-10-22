package ua.training.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.command.CommandUtility;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static ua.training.controller.Attribute.SESSION_EMAIL;

public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.debug("Session listener creation starts");
        // do nothing
        logger.debug("Session listener creation finished");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.debug("Session listener destruction starts");
        HttpSession session = se.getSession();
        CommandUtility.removeFromLoggedUsers(session, (String) session.getAttribute(SESSION_EMAIL));
        logger.debug("Session listener destruction finished");
    }
}
