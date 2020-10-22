package ua.training.model.service;

import ua.training.exception.db.DBException;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.FacultyDao;
import ua.training.model.entity.Faculty;
import ua.training.model.entity.Request;
import ua.training.model.enumeration.FacultyComparator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FacultyService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void deleteFromUniversity(int universityId, int facultyId, Request.State state) throws DBException, SQLException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            facultyDao.deleteFromUniversity(universityId, facultyId, state);
        }
    }

    public void updateFacultyForUniversity(int universityId, Faculty faculty) throws DBException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            facultyDao.updateFacultyForUniversity(universityId, faculty);
        }
    }

    public List<Faculty> findFacultiesNameForUniversity(int universityId, String lang) throws DBException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findFacultiesNameForUniversity(universityId, lang);
        }
    }

    public List<Faculty> findFacultiesForUniversity(int universityId, String lang) throws DBException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findFacultiesForUniversity(universityId, lang);
        }
    }

    public Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang) throws DBException, SQLException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findFacultiesForStudent(userId, lang);
        }
    }

    public Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang, FacultyComparator sort) throws DBException, SQLException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findFacultiesForStudent(userId, lang, sort);
        }
    }

    public void setFacultyForUniversity(int universityId, Faculty faculty) throws DBException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            facultyDao.setFacultyForUniversity(universityId, faculty);
        }
    }

    public List<Faculty> findFacultiesUniversityNotHave(int universityId, String lang) throws DBException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findFacultiesForUniversityNotHave(universityId, lang);
        }
    }

    public Optional<List<Faculty>> findAll() throws DBException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findAll();
        }
    }

    public void finalizeFaculty(int facultyId) throws DBException {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            facultyDao.finalizeFaculty(facultyId);
        }
    }
}
