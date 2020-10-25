package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.model.entity.Account;
import ua.training.model.entity.StudyAccount;
import ua.training.model.entity.University;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.Encryptor;
import ua.training.util.NumericParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static ua.training.controller.Attribute.*;
import static ua.training.controller.FieldConst.*;
import static ua.training.controller.Message.*;
import static ua.training.controller.Path.REDIRECT_TO_LOG_IN;
import static ua.training.controller.Path.REDIRECT_TO_SIGN_UP;
import static ua.training.exception.Message.USER_SERVICE_ERROR;
import static ua.training.exception.Message.VALIDATION_FAILED;


public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private UserService userService = new UserService();

    private User getUserFromForm(HttpServletRequest req) {
        StudyAccount studyAccount = new StudyAccount(
                new University(NumericParser.parseInt(req.getParameter(REQUEST_UNIVERSITY))), NumericParser.parseFloat(req.getParameter(REQUEST_AVERAGE_SCORE)));

        Account account = new Account.AccountBuilder()
                .setFirstName(req.getParameter(REQUEST_FIRST_NAME))
                .setLastName(req.getParameter(REQUEST_LAST_NAME))
                .setMiddleName(req.getParameter(REQUEST_MIDDLE_NAME))
                .setCity(req.getParameter(REQUEST_CITY))
                .setRegion(req.getParameter(REQUEST_REGION))
                .build();

        return new User.UserBuilder()
                .setEmail(req.getParameter(REQUEST_EMAIL))
                .setPassword(req.getParameter(REQUEST_PASS))
                .setAccount(account)
                .setStudyAccount(studyAccount)
                .build();
    }

    private boolean validateData(User user) {
        return user.getEmail() == null || user.getPassword() == null
                || user.getStudyAccount().getUniversity().getId() == PARSE_NUMBER_FAILED
                || user.getStudyAccount().getAverageScore() == PARSE_NUMBER_FAILED
                || user.getAccount().getFirstName() == null
                || user.getAccount().getLastName() == null
                || user.getAccount().getMiddleName() == null
                || user.getAccount().getCity() == null
                || user.getAccount().getRegion() == null
                || !user.getEmail().matches(REGEX_EMAIL)
                || user.getPassword().length() < MIN_PASS_LENGTH
                || user.getPassword().length() > MAX_PASS_LENGTH
                || user.getStudyAccount().getAverageScore() < MIN_AVERAGE_SCORE
                || user.getStudyAccount().getAverageScore() > MAX_AVERAGE_SCORE
                || user.getAccount().getFirstName().length() < MIN_FIRST_NAME_LENGTH
                || user.getAccount().getLastName().length() < MIN_LAST_NAME_LENGTH
                || user.getAccount().getMiddleName().length() < MIN_MIDDLE_NAME_LENGTH
                || user.getAccount().getCity().length() < MIN_CITY_LENGTH
                || user.getAccount().getRegion().length() < MIN_REGION_LENGTH;
    }

    @Override
    public String execute(HttpServletRequest req) {
        logger.info("Executing SignUpCommand");

        User user = getUserFromForm(req);

        HttpSession session = req.getSession();
        CommandUtility.setSession(session,user, TRIED_USER_SIGNUP);

        if (validateData(user)) {
            logger.info(VALIDATION_FAILED);
            CommandUtility.setSession(req.getSession(), MESSAGE_ACTION_FORM_INCORRECT, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_SIGN_UP;
        }

        user.updatePassword(Encryptor.cryptWithMD5(user.getPassword()));

        try {
            userService.create(user);
        } catch (DBException e) {
            logger.error(USER_SERVICE_ERROR, e);
            CommandUtility.setSession(session, MESSAGE_ACTION_FORM_CHOOSE_EMAIL, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_SIGN_UP;
        } catch (SQLException e) {
            logger.error(USER_SERVICE_ERROR, e);
            CommandUtility.setSession(session, MESSAGE_ACTION_APP_EXCEPTION_ERROR, SESSION_ERROR_MESSAGE_ACTIVITY);
            return REDIRECT_TO_SIGN_UP;
        }

        CommandUtility.setSession(session, MESSAGE_ACTION_FORM_SIGNUP_SUCCESSFUL, SESSION_ERROR_MESSAGE_ACTIVITY);
        return REDIRECT_TO_LOG_IN;
    }
}
