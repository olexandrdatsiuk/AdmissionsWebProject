package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.exception.db.UserNotExistsException;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Account;
import ua.training.model.entity.StudyAccount;
import ua.training.model.entity.University;
import ua.training.model.entity.User;
import ua.training.model.enumeration.UserRole;
import ua.training.model.enumeration.UserStatus;

import java.sql.*;
import java.util.Optional;

import static ua.training.controller.Message.MESSAGE_ACTION_FORM_BLOCKED_USER;
import static ua.training.controller.Message.MESSAGE_ACTION_FORM_INCORRECT_LOGIN_DATA;
import static ua.training.exception.Message.DAO_EXCEPTION_THROWN;
import static ua.training.model.sql.JDBCQuery.*;

public class JDBCUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws DBException, SQLException {
        ResultSet keys = null;
        try (
                PreparedStatement psForStudyAccount = connection.prepareStatement(INSERT_INTO_STUDY_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psForAccount = connection.prepareStatement(INSERT_INTO_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psForPerson = connection.prepareStatement(INSERT_INTO_PERSON)
        ) {
            StudyAccount studyAccount = user.getStudyAccount();
            psForStudyAccount.setInt(1, studyAccount.getUniversity().getId());
            psForStudyAccount.setFloat(2, studyAccount.getAverageScore());

            Account account = user.getAccount();
            psForAccount.setString(1, account.getFirstName());
            psForAccount.setString(2, account.getLastName());
            psForAccount.setString(3, account.getMiddleName());
            psForAccount.setString(4, account.getCity());
            psForAccount.setString(5, account.getRegion());

            psForPerson.setString(1, user.getEmail());
            psForPerson.setString(2, user.getPassword());

            connection.setAutoCommit(false);

            psForStudyAccount.executeUpdate();
            keys = psForStudyAccount.getGeneratedKeys();
            keys.next();
            psForPerson.setInt(4, keys.getInt(1));

            psForAccount.executeUpdate();
            keys = psForAccount.getGeneratedKeys();
            keys.next();
            psForPerson.setInt(3, keys.getInt(1));
            psForPerson.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw new DBException(e);
        } finally {
            close(keys);
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Optional<User> findStudyAccountDetails(int userId, String lang) {
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(String.format(FIND_STUDY_ACCOUNT_DETAILS, lang))) {
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            rs.next();

            StudyAccount studyAccount = new StudyAccount(new University(rs.getString(1)), rs.getInt(2));
            User user = new User.UserBuilder().setStudyAccount(studyAccount).build();
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return Optional.empty();
    }

    @Override
    public User loginByEmailAndPass(String email, String pass) throws DBException {
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(LOGIN_BY_EMAIL_PASSWORD)) {
            ps.setString(1, email);
            ps.setString(2, pass);

            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new UserNotExistsException(MESSAGE_ACTION_FORM_INCORRECT_LOGIN_DATA);
            }
            if (rs.getInt(4) == UserStatus.BLOCKED.getStatus()) {
                throw new UserNotExistsException(MESSAGE_ACTION_FORM_BLOCKED_USER);
            }

            return new User.UserBuilder()
                    .setId(rs.getInt(1))
                    .setEmail(rs.getString(2))
                    .setRole(UserRole.getRoleByNumber(rs.getInt(3)))
                    .build();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            close(rs);
        }
    }

    @Override
    public Optional<User> findAccountDetails(int userId) {
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_ACCOUNT_DETAILS)) {
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            rs.next();
            Account account = new Account.AccountBuilder()
                    .setFirstName(rs.getString(2))
                    .setLastName(rs.getString(3))
                    .setMiddleName(rs.getString(4))
                    .setCity(rs.getString(5))
                    .setRegion(rs.getString(6))
                    .build();
            User user = new User.UserBuilder().setEmail(rs.getString(1)).setAccount(account).build();
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return Optional.empty();
    }

    @Override
    public void changeStatus(String email, UserStatus status, int userId) throws DBException {
        try (PreparedStatement ps = connection.prepareStatement(BLOCK_USER_BY_EMAIL)) {
            ps.setInt(1, status.getStatus());
            ps.setString(2, email);
            ps.setInt(3, userId);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                throw new UserNotExistsException();
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void close() {
        close(connection);
    }

    private void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                logger.error(DAO_EXCEPTION_THROWN, e);
            }
        }
    }
}
