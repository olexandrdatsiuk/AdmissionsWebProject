package ua.training.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Servlet.class);

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put(CommandTrack.LOG_IN, new LogInCommand());
        commands.put(CommandTrack.LOG_OUT, new LogOutCommand());
        commands.put(CommandTrack.SIGN_UP, new SignUpCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String extractCommandPattern = ".*/AdmissionsWebProject/";
        final String defaultPath = "/index.jsp";

        logger.debug("request starts ->>>");

        String path = req.getRequestURI();

        path = path.replaceAll(extractCommandPattern, "");

        Command command = commands.getOrDefault(path, p -> "redirect:" + defaultPath);
        logger.debug(command.getClass().getName());

        String page = command.execute(req);

        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replaceAll("redirect:", "/AdmissionsWebProject"));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }

        logger.debug("<<<- request ends");
    }
}
