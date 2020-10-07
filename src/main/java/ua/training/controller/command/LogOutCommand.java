package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.filter.AuthFilter.Role;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("LogOutCommand.execute");
        CommandUtility.setSession(request.getSession(), Role.GUEST, "role");
        CommandUtility.removeFromLoggedUsers(request.getSession(), (String) request.getSession().getAttribute("userName"));

        return "redirect:/index.jsp";
    }
}
