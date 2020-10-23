package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.model.dao.SubjectDao;
import ua.training.model.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.training.exception.Message.DAO_EXCEPTION_THROWN;
import static ua.training.model.sql.JDBCQuery.*;

public class JDBCSubjectDao implements SubjectDao {
    private static final Logger logger = LogManager.getLogger(JDBCSubjectDao.class);

    private Connection connection;

    public JDBCSubjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<List<Subject>> getSubjectsNames(String lang) {
        List<Subject> fList = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(String.format(SUBJECTS_NAMES, lang))) {
            while (rs.next()) {
                fList.add(new Subject(rs.getInt(1), rs.getString(2)));
            }
            return Optional.of(fList);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Subject>> findSubjectsUserNotHave(int userId, String lang) {
        List<Subject> fList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(String.format(SUBJECTS_FOR_USER_NOT_HAVE, lang))
        ) {
            st.setInt(1, userId);
            rs = st.executeQuery();
            while (rs.next()) {
                fList.add(new Subject(rs.getInt(1), rs.getString(2)));
            }
            return Optional.of(fList);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }

        return Optional.empty();
    }

    @Override
    public void setSubjectForUser(Subject subject) throws DBException {
        try (PreparedStatement ps = connection.prepareStatement(SET_SUBJECT_FOR_USER)) {
            ps.setInt(1, subject.getPerson_id());
            ps.setInt(2, subject.getId());
            ps.setInt(3, subject.getScore());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Optional<List<Subject>> findSubjectsForUser(int userId, String lang) {
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(String.format(FIND_SUBJECTS_NAME_SCORE_FOR_USER, lang))) {
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            List<Subject> subjects = new ArrayList<>();
            while (rs.next()) {
                subjects.add(new Subject(rs.getString(1), rs.getInt(2)));
            }
            return Optional.of(subjects);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return Optional.empty();
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
