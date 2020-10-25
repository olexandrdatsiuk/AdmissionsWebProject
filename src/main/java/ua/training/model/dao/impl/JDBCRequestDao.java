package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.FieldConst;
import ua.training.model.dao.RequestDao;
import ua.training.model.entity.Faculty;
import ua.training.model.entity.Request;
import ua.training.model.entity.University;
import ua.training.model.entity.User;
import ua.training.model.enumeration.RequestState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.training.exception.Message.DAO_EXCEPTION_THROWN;
import static ua.training.model.sql.JDBCQuery.*;

public class JDBCRequestDao implements RequestDao {
    private static final Logger logger = LogManager.getLogger(JDBCRequestDao.class);

    private Connection conn;

    private static final int maxRequestsFromDb = FieldConst.MAX_REQUESTS_ON_PAGE + 1;

    public JDBCRequestDao(Connection connection) {
        this.conn = connection;
    }


    @Override
    public void changeRequestStatus(int userId, int facultyId, int state) {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE_REQUEST_STATE)) {
            ps.setInt(1, state);
            ps.setInt(2, state);
            ps.setInt(3, userId);
            ps.setInt(4, facultyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        }
    }

    @Override
    public Optional<List<Request>> findAll(int startFrom, String lang) {
        List<Request> requests = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = conn.prepareStatement(String.format(FIND_ALL_REQUESTS, lang))) {
            ps.setInt(1, RequestState.CONSIDERED.getState());
            ps.setInt(2, startFrom);
            ps.setInt(3, maxRequestsFromDb);

            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User.UserBuilder().setId(rs.getInt(1)).setEmail(rs.getString(2)).build();
                University un = new University(rs.getString(3));
                Faculty f = new Faculty.FacultyBuilder().setName(rs.getString(4)).setId(rs.getInt(5)).build();
                Request r = new Request.RequestBuilder().setUser(u).setFaculty(f).setUniversity(un).setState(rs.getString(6)).build();
                requests.add(r);
            }
            return Optional.of(requests);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Request>> findForUser(int userId, String lang) {
        ResultSet rs = null;
        List<Request> requests = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(String.format(FIND_REQUESTS_FOR_USER, lang))) {
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                requests.add(new Request(rs.getString(1), new Faculty.FacultyBuilder().setName(rs.getString(2)).build()));
            }
            return Optional.of(requests);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return Optional.empty();
    }

    @Override
    public void create(int userId, int facultyId, String lang) {
        try (PreparedStatement ps = conn.prepareStatement(CREATE_REQUEST)) {
            ps.setInt(1, userId);
            ps.setInt(2, facultyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        }
    }

    @Override
    public void close() {
        close(conn);
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
