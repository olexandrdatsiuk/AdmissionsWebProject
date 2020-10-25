package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.Message;
import ua.training.model.enumeration.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

import static ua.training.controller.Attribute.CONTEXT_LOGGED_USERS;
import static ua.training.controller.Attribute.SESSION_ROLE;

/**
 * Represents a CommandUtility.
 * CommandUtility is an utility class
 * that contains methods helps process
 * controller executing.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class CommandUtility {
    private static final Logger logger = LogManager.getLogger(CommandUtility.class);

    private CommandUtility() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    /**
     * Returns user`s role from session.
     *
     * @param request the ServletRequest object contains the client's request
     * @return A UserRole representing the role of the user.
     * GUEST - if session attribute is null or GUEST.
     * Otherwise - another role except GUEST
     */
    public static UserRole getUserRole(HttpServletRequest request) {
        logger.info("Getting user`s role");
        HttpSession session = request.getSession();
        Object roleFromSession = session.getAttribute(SESSION_ROLE);
        if (roleFromSession == null) {
            setSession(session, UserRole.GUEST, SESSION_ROLE);
            return UserRole.GUEST;
        }
        return (UserRole) roleFromSession;
    }

    /**
     * Updates attribute session.
     *
     * @param session the HttpSession object contains the client's session
     * @param o       the Object object to assign to attribute
     * @param attr    a String contains the attribute name to update
     */
    public static void setSession(HttpSession session, Object o, String attr) {
        logger.info("Setting session attribute: " + attr);
        session.setAttribute(attr, o);
    }

    /**
     * Checks if a user is already logged in system.
     *
     * @param session the HttpSession object contains the client's session
     * @param name    a String contains the unique user login
     * @return true - if user is logged in system
     */
    static boolean isUserLogged(HttpSession session, String name) {
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute(CONTEXT_LOGGED_USERS);
        boolean is = !loggedUsers.add(name);
        logger.info(name + "is logged: " + is);
        return is;
    }

    /**
     * Removes user from list of logged in system users.
     *
     * @param session the HttpSession object contains the client's session
     * @param name    a String contains the unique user login
     */
    public static void removeFromLoggedUsers(HttpSession session, String name) {
        logger.info("Removing user from logged users list: " + name);
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute(CONTEXT_LOGGED_USERS);
        loggedUsers.remove(name);
    }
}
