package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.filter.AuthFilter.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class LogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogInCommand.class);

    class User {
        String name;
        String pass;
        Role role;

        public User(String name, String pass, Role role) {
            this.name = name;
            this.pass = pass;
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(name, user.name) &&
                    Objects.equals(pass, user.pass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, pass);
        }
    }


    @Override
    public String execute(HttpServletRequest req) {
        logger.debug("LogInCommand.execute");

        String name = req.getParameter("name");
        String pass = req.getParameter("pass");

        logger.debug("name [" + name + "]");
        logger.debug("pass [" + pass + "]");

        if (checkNullAndEmpty(name) || checkNullAndEmpty(pass)) {
            return "redirect:/login.jsp";
        }
        // 2 - admin, 1 - user, 0 - guest
        User admin = new User("admin", "1", Role.ADMIN);
        User user = new User("user", "1", Role.USER);

        User now = new User(name, pass, Role.GUEST);

        if (CommandUtility.isUserLogged(req.getSession(), now.name)) {
            logger.debug("user is already logged");
            req.setAttribute("home_path", req.getContextPath() + ((Role) req.getSession().getAttribute("role")).getRedirect());
            return "/WEB-INF/404.jsp";
        }

        if (now.equals(user)) {
            now.role = user.role;
            CommandUtility.setSession(req.getSession(), now.role, "role");
            CommandUtility.setSession(req.getSession(), now.name, "userName");
            return "redirect:/user/basic.jsp";
        }

        if (now.equals(admin)) {
            now.role = admin.role;
            CommandUtility.setSession(req.getSession(), now.role, "role");
            CommandUtility.setSession(req.getSession(), now.name, "userName");
            return "redirect:/admin/basic.jsp";
        }

        return "redirect:/login.jsp";
    }

    private boolean checkNullAndEmpty(String value) {
        return value == null || "".equals(value);
    }
}
