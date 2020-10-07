package ua.training.controller.filter;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ua.training.controller.command.CommandUtility;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    private static Map<Role, List<String>> commandAccess = new HashMap<>();
    private static Map<Role, List<String>> pageAccess = new HashMap<>();

    public enum Role {
        // todo try to change GUEST dir to simplified AuthFilter code
        GUEST(0, "", "/index.jsp"),
        USER(1, "user/", "/user/basic.jsp"),
        ADMIN(2, "admin/", "/admin/basic.jsp");

        private int role;
        private String directory;
        private String redirect;


        Role(int r, String dir, String red) {
            role = r;
            directory = dir;
            redirect = red;
        }

        public int getRole() {
            return role;
        }

        public String getDirectory() {
            return directory;
        }

        public String getRedirect() {
            return redirect;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commandAccess.put(Role.GUEST, Arrays.asList("login", "signup"));
        commandAccess.put(Role.USER, Arrays.asList("logout"));
        commandAccess.put(Role.ADMIN, Arrays.asList("logout"));

        pageAccess.put(Role.GUEST, Arrays.asList("index.jsp", "login.jsp", "signup.jsp"));
        pageAccess.put(Role.USER, Arrays.asList("basic.jsp"));
        pageAccess.put(Role.ADMIN, Arrays.asList("basic.jsp"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("AuthFilter starts...");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        logger.debug("getRequestURI: " + req.getRequestURI());

        Role role = CommandUtility.getUserRole(req);

        logger.debug("This is a " + role);

        final String extractCommandPattern = ".*/AdmissionsWebProject/";
        String path = req.getRequestURI().replaceAll(extractCommandPattern, "");

        logger.debug("replaced path: " + path);
        if (path.length() == 0) {
            logger.debug("send redirect to " + "index.jsp");
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        if (role == Role.GUEST) {
            if (path.endsWith(".jsp") && pageAccess.get(role).contains(path)) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                auth(req, resp, filterChain, role, path);
            }
        } else {
            if (path.startsWith(role.getDirectory())) {
                path = path.replace(role.getDirectory(), "");
            }
            if (path.endsWith(".jsp") && pageAccess.get(role).contains(path)) {
                request.getRequestDispatcher("/WEB-INF/" + role.getDirectory() + path).forward(request, response);
            } else {
                auth(req, resp, filterChain, role, path);
            }
        }

    }

    private void auth(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Role role, String path) throws IOException, ServletException {
        if (commandAccess.get(role).contains(path)) {
            chain.doFilter(req, resp);
        } else {
            logger.debug("ACCESS DENIED");
            logger.debug("send redirect to: " + role.getRedirect());
            resp.sendRedirect(req.getContextPath() + role.getRedirect()); // todo this variant
//                request.getRequestDispatcher(role.getRedirect()).forward(request, response); // todo or this

//            req.setAttribute("home_path", req.getContextPath() + role.getRedirect());
//            req.getRequestDispatcher("/WEB-INF/404.jsp").forward(req, resp); // todo or this
        }
    }
}
