package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.db.DBException;
import ua.training.exception.db.FacultyNotExistsException;
import ua.training.model.dao.FacultyDao;
import ua.training.model.entity.Faculty;
import ua.training.model.entity.Request;
import ua.training.model.entity.Subject;
import ua.training.model.enumeration.FacultyComparator;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static ua.training.controller.Message.MESSAGE_ACTION_FACULTY_NOT_FOUND;
import static ua.training.exception.Message.DAO_EXCEPTION_THROWN;
import static ua.training.model.sql.JDBCQuery.*;

public class JDBCFacultyDao implements FacultyDao {
    private static final Logger logger = LogManager.getLogger(JDBCFacultyDao.class);

    private Connection conn;

    public JDBCFacultyDao(Connection connection) {
        this.conn = connection;
    }

    @Override
    public void finalizeFaculty(int facultyId) throws DBException {
        try (PreparedStatement ps = conn.prepareStatement(FINALIZE_FACULTY)) {
            ps.setInt(1, facultyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Optional<List<Faculty>> findAll() {
        List<Faculty> fList = new ArrayList<>();
        ResultSet rs = null;
        try (Statement st = conn.createStatement()) {
            rs = st.executeQuery(FIND_ALL_FACULTIES);
            while (rs.next()) {
                fList.add(new Faculty.FacultyBuilder()
                        .setId(rs.getInt(1))
                        .setName(rs.getString(2))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return Optional.of(fList);
    }

    @Override
    public Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang, FacultyComparator sort) throws SQLException {
        Optional<List<Faculty>> faculties = findFacultiesForStudent(userId, lang);
        faculties.get().sort(sort);
        return faculties;
    }

    @Override
    public Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang) throws SQLException {
        //todo where to locate setAutoCommit ???
        ResultSet rs = null;
        List<Faculty> userFaculties;
        try (
                PreparedStatement psForFacultiesSubjects = conn.prepareStatement(String.format(FIND_FACULTIES_FOR_USER_BY_UNIVERSITY, lang));
                PreparedStatement psForStudentSubjects = conn.prepareStatement(FIND_SUBJECTS_ID_FOR_USER);
        ) {
            psForFacultiesSubjects.setInt(1, userId);
            psForFacultiesSubjects.setInt(2, userId);
            psForStudentSubjects.setInt(1, userId);

            Map<Integer, Subject> subjects = new HashMap<>();

            conn.setAutoCommit(false);

            rs = psForStudentSubjects.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                makeUniqueSubject(subjects, new Subject(id));
            }
            List<Subject> userSubjects = new ArrayList<>(subjects.values());
            Map<Integer, Faculty> faculties = new HashMap<>();

            rs = psForFacultiesSubjects.executeQuery();
            while (rs.next()) {
                int sId = rs.getInt(1);
                Faculty f = new Faculty.FacultyBuilder()
                        .setId(rs.getInt(2))
                        .setName(rs.getString(3))
                        .setFreeAmount(rs.getInt(4))
                        .setAllAmount(rs.getInt(5))
                        .build();
                Subject s = makeUniqueSubject(subjects, new Subject(sId));
                Faculty fac = makeUniqueFaculty(faculties, f);
                fac.getSubjects().add(s);
            }
            userFaculties = faculties.values().stream().filter(v -> userSubjects.containsAll(v.getSubjects())).collect(Collectors.toList());
            conn.commit();
            return Optional.of(userFaculties);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
            conn.rollback();
            userFaculties = new ArrayList<>();
        } finally {
            close(rs);
            conn.setAutoCommit(true);
        }
        return Optional.of(userFaculties);
    }

    private Faculty makeUniqueFaculty(Map<Integer, Faculty> faculties, Faculty faculty) {
        faculties.putIfAbsent(faculty.getId(), faculty);
        return faculties.get(faculty.getId());
    }

    private Subject makeUniqueSubject(Map<Integer, Subject> subjects, Subject subject) {
        subjects.putIfAbsent(subject.getId(), subject);
        return subjects.get(subject.getId());
    }

    @Override
    public void updateFacultyForUniversity(int universityId, Faculty faculty) throws DBException {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE_FACULTY_FOR_UNIVERSITY)) {
            ps.setInt(1, faculty.getFreeAmount());
            ps.setInt(2, faculty.getAllAmount());
            ps.setInt(3, universityId);
            ps.setInt(4, faculty.getId());

            if (ps.executeUpdate() == 0) {
                throw new FacultyNotExistsException(MESSAGE_ACTION_FACULTY_NOT_FOUND);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Faculty> findFacultiesForUniversityNotHave(int universityId, String lang) {
        List<Faculty> fList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement st = conn.prepareStatement(String.format(FACULTIES_FOR_UNIVERSITY_NOT_HAVE, lang))) {
            st.setInt(1, universityId);
            rs = st.executeQuery();
            while (rs.next()) {
                fList.add(new Faculty.FacultyBuilder()
                        .setId(rs.getInt(1))
                        .setName(rs.getString(2))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return fList;
    }


    @Override
    public void deleteFromUniversity(int universityId, int facultyId, Request.State state) throws DBException, SQLException {
        // TODO WHERE TO LOCATE SETaUTOCOMMIT
        try (
                PreparedStatement ps = conn.prepareStatement(DELETE_FACULTY_FOR_UNIVERSITY);
                PreparedStatement ps2 = conn.prepareStatement(UPDATE_REQUESTS_WHEN_DELETE_FROM_UNIVERSITY);
        ) {
            ps.setInt(1, universityId);
            ps.setInt(2, facultyId);

            ps2.setInt(1, state.getState());
            ps2.setInt(2, state.getState());
            ps2.setInt(3, facultyId);

            conn.setAutoCommit(false);
            if (ps.executeUpdate() == 0) {
                throw new FacultyNotExistsException(MESSAGE_ACTION_FACULTY_NOT_FOUND);
            }
            ps2.executeUpdate();
        } catch (SQLException e) {
            conn.rollback();
            throw new DBException(e);
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public void setFacultyForUniversity(int universityId, Faculty faculty) throws DBException {
        try (PreparedStatement psForFacultyForUniversity = conn.prepareStatement(INSERT_INTO_UNIVERSITY_HAS_FACULTY)) {
            psForFacultyForUniversity.setInt(1, universityId);
            psForFacultyForUniversity.setInt(2, faculty.getId());
            psForFacultyForUniversity.setInt(3, faculty.getAllAmount());
            psForFacultyForUniversity.setInt(4, faculty.getFreeAmount());
            if (psForFacultyForUniversity.executeUpdate() == 0) {
                throw new DBException();
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Faculty> findFacultiesForUniversity(int universityId, String lang) {
        List<Faculty> fList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement st = conn.prepareStatement(String.format(FACULTIES_FOR_UNIVERSITY, lang))) {
            st.setInt(1, universityId);
            rs = st.executeQuery();
            while (rs.next()) {
                fList.add(new Faculty.FacultyBuilder()
                        .setId(rs.getInt(1))
                        .setName(rs.getString(2))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return fList;
    }

    @Override
    public List<Faculty> findFacultiesNameForUniversity(int universityId, String lang) {
        List<Faculty> fList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement st = conn.prepareStatement(String.format(FACULTIES_NAMES_FOR_UNIVERSITY, lang))) {
            st.setInt(1, universityId);
            rs = st.executeQuery();
            while (rs.next()) {
                fList.add(new Faculty.FacultyBuilder()
                        .setId(rs.getInt(1))
                        .setName(rs.getString(2))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        } finally {
            close(rs);
        }
        return fList;
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