package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Attribute;
import ua.training.exception.db.DBException;
import ua.training.model.entity.Faculty;
import ua.training.model.enumeration.UniversityAction;
import ua.training.model.service.FacultyService;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ua.training.controller.Attribute.*;
import static ua.training.controller.Message.MESSAGE_ACTION_APP_EXCEPTION_ERROR;
import static ua.training.controller.Path.*;
import static ua.training.exception.Message.FACULTY_SERVICE_ERROR;

public class ManageUniversityCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ManageUniversityCommand.class);

    private FacultyService facultyService = new FacultyService();

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing ManageUniversityCommand");

        String page = REDIRECT_TO_MANAGE_UNIVERSITY;

        String actionStr = req.getParameter(REQUEST_ACTION);
        int universityId;

        if (actionStr == null || (universityId = NumericParser.parseInt(req.getParameter(REQUEST_UNIVERSITY))) == 0) {
            return page;
        }

        UniversityAction action = UniversityAction.getAction(actionStr);
        List<Faculty> faculties = new ArrayList<>();
        try {
            switch (action) {
                case ADD:
                    faculties = facultyService.findFacultiesUniversityNotHave(universityId, (String) req.getSession().getAttribute(Attribute.SESSION_LANG));
                    page = FORWARD_TO_ADD_FACULTY;
                    break;
                case UPDATE:
                    faculties = facultyService.findFacultiesForUniversity(universityId, (String) req.getSession().getAttribute(Attribute.SESSION_LANG));
                    page = FORWARD_TO_UPDATE_FACULTY;
                    break;
                case DELETE:
                    faculties = facultyService.findFacultiesNameForUniversity(universityId, (String) req.getSession().getAttribute(Attribute.SESSION_LANG));
                    page = FORWARD_TO_DELETE_FACULTY;
                    break;
                default:
                    // do nothing
                    break;
            }
        } catch (DBException e) {
            logger.error(FACULTY_SERVICE_ERROR, e);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
        }
        req.setAttribute(REQUEST_FACULTIES, faculties);
        return page;
    }
}
