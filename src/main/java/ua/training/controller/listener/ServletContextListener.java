package ua.training.controller.listener;


import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import java.util.HashSet;

public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("loggedUsers", new HashSet<String>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // todo set attr 'loggedUsers' to null
        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }
}
