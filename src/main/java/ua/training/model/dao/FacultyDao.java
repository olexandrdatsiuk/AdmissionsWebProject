package ua.training.model.dao;

import ua.training.exception.db.DBException;
import ua.training.model.entity.Faculty;
import ua.training.model.entity.Request;
import ua.training.model.enumeration.FacultyComparator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FacultyDao extends GenericDao<FacultyDao> {
    List<Faculty> findFacultiesNameForUniversity(int universityId, String lang);

    List<Faculty> findFacultiesForUniversity(int universityId, String lang);

    void deleteFromUniversity(int universityId, int facultyId, Request.State state) throws DBException, SQLException;

    void setFacultyForUniversity(int universityId, Faculty faculty) throws DBException;

    List<Faculty> findFacultiesForUniversityNotHave(int universityId, String lang);

    void updateFacultyForUniversity(int universityId, Faculty faculty) throws DBException;

    Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang, FacultyComparator sort) throws SQLException;

    Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang) throws SQLException;

    Optional<List<Faculty>> findAll();

    void finalizeFaculty(int facultyId) throws DBException;
}
