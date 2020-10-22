package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.Message;
import ua.training.model.entity.User.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

import static ua.training.controller.Attribute.CONTEXT_LOGGED_USERS;
import static ua.training.controller.Attribute.SESSION_ROLE;

public class CommandUtility {
    private static final Logger logger = LogManager.getLogger(CommandUtility.class);

    private CommandUtility() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static Role getUserRole(HttpServletRequest request) {
        logger.info("Getting user`s role");
        HttpSession session = request.getSession();
        Object roleFromSession = session.getAttribute(SESSION_ROLE);
        if (roleFromSession == null) {
            setSession(session, Role.GUEST, SESSION_ROLE);
            return Role.GUEST;
        }
        return (Role) roleFromSession;
    }

    public static void setSession(HttpSession session, Object o, String attr) {
        logger.info("Setting session attribute: " + attr);
        session.setAttribute(attr, o);
    }

    static boolean isUserLogged(HttpSession session, String name) {
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute(CONTEXT_LOGGED_USERS);
        boolean is = !loggedUsers.add(name);
        logger.info(name + "is logged: " + is);
        return is;
    }

    public static void removeFromLoggedUsers(HttpSession session, String name) {
        logger.info("Removing user from logged users list: " + name);
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute(CONTEXT_LOGGED_USERS);
        loggedUsers.remove(name);
    }
}
