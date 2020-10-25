package ua.training.controller.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.enumeration.FacultyComparator;
import ua.training.model.enumeration.RequestState;
import ua.training.model.enumeration.UniversityAction;

import javax.servlet.ServletContextEvent;
import java.util.Arrays;
import java.util.HashSet;

import static ua.training.controller.Attribute.*;

public class ServletContextListener implements javax.servlet.ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("Context listener initialization starts");
        sce.getServletContext().setAttribute(CONTEXT_LOGGED_USERS, new HashSet<String>());
        sce.getServletContext().setAttribute(CONTEXT_REQUEST_STATES, Arrays.asList(RequestState.REJECTED, RequestState.ACCEPTED));
        sce.getServletContext().setAttribute(CONTEXT_FACULTY_COMPARATOR, FacultyComparator.values());
        sce.getServletContext().setAttribute(CONTEXT_UNIVERSITY_ACTION, UniversityAction.values());
        logger.debug("Context listener initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("Context listener destruction starts");
        logger.debug("Trying to clean connection pool threads");
        AbandonedConnectionCleanupThread.checkedShutdown();
        logger.debug("Context listener destruction finished");
    }
}
