package ua.training.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Attribute;
import ua.training.controller.FieldConst;
import ua.training.exception.db.DBException;
import ua.training.model.entity.*;
import ua.training.model.enumeration.FacultyComparator;
import ua.training.model.service.*;
import ua.training.util.NumericParser;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.SQLException;
import java.util.*;

import static ua.training.controller.Attribute.*;
import static ua.training.exception.Message.SQL_HANDLER_TAG_EXCEPTION_THROWN;

/**
 * SQLHandler class defines new tag handler implementing Tag.
 * The SQLHandler class intended to be used as retrieving the data through
 * one of the dao services to for using them in jsp pages. All data puts
 * in request as attribute with specified name.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class SQLHandler extends TagSupport {
    private static final Logger logger = LogManager.getLogger(SQLHandler.class);

    private static final Object ATTRIBUTE_CAP = 1;
    private static final Optional<?> dataCap = Optional.of(new ArrayList<>());

    private UserService userService = new UserService();
    private UniversityService universityService = new UniversityService();
    private FacultyService facultyService = new FacultyService();
    private SubjectService subjectService = new SubjectService();
    private RequestService requestService = new RequestService();

    private Map<String, Executable> tasks = new HashMap<>();

    private String task;
    private String attr;

    @FunctionalInterface
    private interface Executable {
        Optional<?> execute() throws DBException;
    }

    /**
     * Creates an SQLHandler instance.
     */
    public SQLHandler() {
        super();
        logger.debug("Handler construction starts");
        tasks.put("getUserAccDetails", this::getUserAccDetails);
        tasks.put("getAllFaculties", this::getAllFaculties);
        tasks.put("getAllUniversities", this::getAllUniversities);
        tasks.put("getFacultiesPerUserByUniversity", this::getFacultiesPerUserByUniversity);
        tasks.put("getUserStudyAccDetails", this::getUserStudyAccDetails);
        tasks.put("getSubjectsPerUser", this::getSubjectsPerUser);
        tasks.put("getSubjectsUserNotHave", this::getSubjectsUserNotHave);
        tasks.put("getSubjectsNames", this::getSubjectsNames);
        tasks.put("getRequestsForUser", this::getRequestsForUser);
        tasks.put("getAllRequestsWithPagination", this::getAllRequestsWithPagination);
        logger.debug("Handler construction finished");
    }

    /**
     * Sets the sql task’s name.
     *
     * @param task A String containing the
     *             name of the task to be executed.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Sets the attribute.
     *
     * @param attr A String containing the name of attribute.
     */
    public void setAttr(String attr) {
        this.attr = attr;
    }

    private Optional<User> getUserAccDetails() {
        int i = (int) pageContext.getSession().getAttribute(SESSION_USER_ID);
        try {
            return userService.findAccountDetails(i);
        } catch (DBException e) {
            logger.error(SQL_HANDLER_TAG_EXCEPTION_THROWN, e);
            return Optional.of(User.EMPTY);
        }
    }

    private Optional<List<Faculty>> getAllFaculties() throws DBException {
        return facultyService.findAll();
    }

    private Optional<List<University>> getAllUniversities() throws DBException {
        return universityService.findAll((String) pageContext.getSession().getAttribute(Attribute.SESSION_LANG));
    }

    private Optional<List<Faculty>> getFacultiesPerUserByUniversity() throws DBException {
        String sort = pageContext.getRequest().getParameter(REQUEST_SORT);
        HttpSession session = pageContext.getSession();
        try {
            return facultyService.findFacultiesForStudent((int) session.getAttribute(SESSION_USER_ID), (String) session.getAttribute(Attribute.SESSION_LANG),
                    sort == null ? FacultyComparator.AZ : FacultyComparator.getComparator(sort)
            );
        } catch (SQLException e) {
            return Optional.of(Collections.emptyList());
        }
    }

    private Optional<User> getUserStudyAccDetails() {
        HttpSession session = pageContext.getSession();
        try {
            return userService.findStudyAccountDetails((int) session.getAttribute(SESSION_USER_ID), (String) session.getAttribute(Attribute.SESSION_LANG));
        } catch (DBException e) {
            logger.error(SQL_HANDLER_TAG_EXCEPTION_THROWN, e);
            return Optional.of(User.EMPTY);
        }
    }

    private Optional<List<Subject>> getSubjectsPerUser() throws DBException {
        HttpSession session = pageContext.getSession();
        return subjectService.findSubjectsForUser((int) session.getAttribute(SESSION_USER_ID), (String) session.getAttribute(Attribute.SESSION_LANG));
    }

    private Optional<List<Subject>> getSubjectsUserNotHave() throws DBException {
        HttpSession session = pageContext.getSession();
        return subjectService.findSubjectsUserNotHave((int) session.getAttribute(SESSION_USER_ID), (String) session.getAttribute(Attribute.SESSION_LANG));
    }

    private Optional<List<Subject>> getSubjectsNames() throws DBException {
        return subjectService.getSubjectsNames((String) pageContext.getSession().getAttribute(Attribute.SESSION_LANG));
    }

    private Optional<List<Request>> getRequestsForUser() throws DBException {
        HttpSession session = pageContext.getSession();
        return requestService.findForUser((int) session.getAttribute(SESSION_USER_ID), (String) session.getAttribute(Attribute.SESSION_LANG));
    }

    private Optional<List<Request>> getAllRequestsWithPagination() throws DBException {
        ServletRequest req = pageContext.getRequest();
        HttpSession session = pageContext.getSession();
        int startFrom = NumericParser.parseInt(req.getParameter(REQUEST_START_FROM));
        session.setAttribute(REQUEST_LAST_START_FROM, startFrom);
        Optional<List<Request>> optionalRequests = requestService.findAll(startFrom, (String) session.getAttribute(Attribute.SESSION_LANG));
        List<Request> requests = optionalRequests.get();
        if (requests.size() > FieldConst.MAX_REQUESTS_ON_PAGE) {
            req.setAttribute(REQUEST_BUTTON_NEXT, ATTRIBUTE_CAP);
            requests.remove(requests.size() - 1);
        }
        return optionalRequests;
    }

    @Override
    public int doStartTag() {
        logger.debug("Processing of the start tag starts");
        logger.info("Processing the task: " + task);

        if (!tasks.containsKey(task)) {
            throw new IllegalArgumentException("There is no such task! Passed task is: " + task);
        }

        Optional<?> data;
        try {
            data = tasks.get(task).execute();
        } catch (DBException e) {
            logger.error(SQL_HANDLER_TAG_EXCEPTION_THROWN, e);
            data = dataCap;
        }
        pageContext.getRequest().setAttribute(attr, data.get());

        logger.debug("Processing of the start tag finished");
        return SKIP_BODY;
    }
}
