package ua.training.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.command.*;
import ua.training.util.RegexHelper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static ua.training.controller.AppConst.*;
import static ua.training.controller.Path.*;

public class Servlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Servlet.class);

    private Map<String, Command> commands = new HashMap<>();

    private Map<String, String> resources = new HashMap<>();

    @Override
    public void init() {
        logger.debug("Servlet initialization starts");
        commands.put(CommandTrack.LOG_IN, new LogInCommand());
        commands.put(CommandTrack.LOG_OUT, new LogOutCommand());
        commands.put(CommandTrack.SIGN_UP, new SignUpCommand());
        commands.put(CommandTrack.ADD_SUBJECT, new AddSubjectCommand());
        commands.put(CommandTrack.BLOCK_USER, new BlockUserCommand());
        commands.put(CommandTrack.UNBLOCK_USER, new UnBlockUserCommand());
        commands.put(CommandTrack.ADD_FACULTY, new AddFacultyCommand());
        commands.put(CommandTrack.MANAGE_FACULTY, new ManageUniversityCommand());
        commands.put(CommandTrack.DELETE_FACULTY, new DeleteFacultyCommand());
        commands.put(CommandTrack.UPDATE_FACULTY, new UpdateFacultyCommand());
        commands.put(CommandTrack.APPLY, new ApplyCommand());
        commands.put(CommandTrack.CHANGE_REQUEST_STATE, new ChangeRequestStateCommand());
        commands.put(CommandTrack.FINALIZE, new FinalizeCommand());

        resources.put(".jpg", "image/jpeg");
        resources.put(".css", "text/css");
        resources.put(".js", "image/jpeg");
        resources.put("text/javascript", "image/jpeg");
        logger.debug("Servlet initialization finished");
    }

    private void loadResource(HttpServletResponse resp, String resource) throws IOException {
        String fileExt = RegexHelper.getStrWithRegex(REGEX_RESOURCE_FILE_EXTENSION, resource);
        java.nio.file.Path path = Paths.get(PATH_TO_RESOURCE_DIRECTORY + resource);
        if (resources.containsKey(fileExt) && Files.exists(path)) {
            logger.debug("Loading resource.");
            ServletOutputStream out = resp.getOutputStream();
            resp.setContentType(resources.get(fileExt));
            resp.setHeader("Content-Disposition", "attachment; filename=\"\"");
            out.write(Files.readAllBytes(path));
            out.flush();
            out.close();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resource = req.getRequestURI().replaceAll(REGEX_REMOVE_APP_DIRECTORY, "");
        if (resource.startsWith(RESOURCE)) {
            loadResource(resp, resource);
            return;
        }
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Processing of request starts");
        String path = req.getRequestURI();
        path = path.replaceAll(REGEX_REMOVE_UP_TO_FILE_OR_COMMAND, "");
        Command command = commands.getOrDefault(path, p -> REDIRECTS + INDEX);
        logger.info("command: " + path + " - " + command.getClass().getName());

        String page = command.execute(req);
        System.out.println(page + " -page <<<<<<<<<<<");
        if (page.contains(Path.REDIRECT)) {
            logger.info("Sending " + page);
            resp.sendRedirect(page.replaceAll(Path.REDIRECT, CONTEXT));
        } else {
            logger.info("Forwarding to " + page);
            req.getRequestDispatcher(page).forward(req, resp);
        }
        logger.debug("Processing of request finished");
    }

}
