package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Path;
import ua.training.controller.command.CommandTrack;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.User.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.training.controller.AppConst.REGEX_REMOVE_APP_DIRECTORY;
import static ua.training.controller.Path.*;
import static ua.training.controller.command.CommandTrack.*;

public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    private static Map<Role, List<String>> commandAccess = new HashMap<>();
    private static Map<Role, List<String>> pageAccess = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Auth filter initialization starts");

        commandAccess.put(Role.GUEST, Arrays.asList(CommandTrack.LOG_IN, CommandTrack.SIGN_UP));
        commandAccess.put(Role.USER, Arrays.asList(LOG_OUT, CommandTrack.ADD_SUBJECT, CommandTrack.APPLY));
        commandAccess.put(Role.ADMIN, Arrays.asList(LOG_OUT, BLOCK_USER, UNBLOCK_USER, CommandTrack.ADD_FACULTY, MANAGE_FACULTY, CommandTrack.DELETE_FACULTY, CommandTrack.UPDATE_FACULTY, CHANGE_REQUEST_STATE, CommandTrack.FINALIZE));

        pageAccess.put(Role.GUEST, Arrays.asList(INDEX, Path.LOG_IN, Path.SIGN_UP));
        pageAccess.put(Role.USER, Arrays.asList(BASIC, Path.APPLY, SUBJECTS, APPLIES, Path.ADD_SUBJECT));
        pageAccess.put(Role.ADMIN, Arrays.asList(BASIC, BLOCK, UNBLOCK, MANAGE_UNIVERSITY, REQUESTS, Path.FINALIZE));

        logger.debug("Auth filter initialization starts");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Processing of the auth filter starts");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI().replaceAll(REGEX_REMOVE_APP_DIRECTORY, "");

        logger.info("Requested path: " + path);

        if (path.length() == 0) {
            logger.debug("send redirect to " + INDEX);
            resp.sendRedirect(INDEX);
            return;
        }

        if (path.startsWith(RESOURCE)) {
            filterChain.doFilter(request, response);
            return;
        }

        confirmAccess(req, resp, filterChain, path);
        logger.debug("Processing of the auth filter finished");
    }

    private void confirmAccess(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, String path) throws IOException, ServletException {
        logger.debug("Access confirmation starts");

        Role role = CommandUtility.getUserRole(req);

        logger.debug("This is a " + role);

        String forwardUrl = path;

        if (role != Role.GUEST && path.startsWith(role.getDirectory())) {
            path = path.replaceAll("^" + role.getDirectory(), "");
            forwardUrl = WEB_INF + role.getDirectory() + path;
        }

        if (path.endsWith(".jsp") && pageAccess.get(role).contains(path)) {
            logger.info("requested page:" + path);
            logger.info("loading page:" + forwardUrl);
            req.getRequestDispatcher(forwardUrl).forward(req, resp);
        } else if (commandAccess.get(role).contains(path)) {
            logger.info("requested command:" + path);
            chain.doFilter(req, resp);
        } else {
            logger.debug("ACCESS DENIED to page/command: " + path);
            logger.debug("send redirect to main role`s page: " + role.getRedirect());
            resp.sendRedirect(req.getContextPath() + role.getRedirect());
        }
        logger.debug("Access confirmation finished");
    }

    @Override
    public void destroy() {
        logger.debug("Auth filter destruction starts");
        // do nothing
        logger.debug("Auth filter destruction finished");
    }
}