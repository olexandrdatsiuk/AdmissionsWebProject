package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.filter.AuthFilter.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    private static final Logger logger = LogManager.getLogger(CommandUtility.class);

    private CommandUtility() {
        throw new AssertionError("This constructor is not for you");
    }

    public static Role getUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object roleFromSession = session.getAttribute("role");
        if (roleFromSession == null) {
            setSession(session, Role.GUEST, "role");
            return Role.GUEST;
        }
        return (Role) roleFromSession;
    }

    public static void setSession(HttpSession session, Object o, String attr) {
        session.setAttribute(attr, o);
    }

    static boolean isUserLogged(HttpSession session, String name) {
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute("loggedUsers");
//        loggedUsers.add(name);
        logger.debug("loggedUsers before: " + loggedUsers);
        boolean is = !loggedUsers.add(name);
        logger.debug("isUserLogged: " + is);
        logger.debug("loggedUsers after: " + loggedUsers);
        return is;
    }

    public static void removeFromLoggedUsers(HttpSession session, String name) {
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute("loggedUsers");
//        loggedUsers.add(name);
        // todo is it necessary to servletContext.setAttr...("loggedUsers", loggedUsers);
        loggedUsers.remove(name);
        logger.debug("loggedUsers: " + loggedUsers);
    }
}
