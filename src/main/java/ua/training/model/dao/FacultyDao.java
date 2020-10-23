package ua.training.model.dao;

import ua.training.exception.db.DBException;
import ua.training.model.entity.Faculty;
import ua.training.model.entity.Request;
import ua.training.model.enumeration.FacultyComparator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * FacultyDao interface provides ten methods to manipulate with Faculty entity.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public interface FacultyDao extends GenericDao<FacultyDao> {
    /**
     * Finds faculties names for university in database.
     *
     * @param universityId An int represents unique university`s id.
     * @param lang         A String represents user`s language.
     * @return List of Faculties data
     */
    List<Faculty> findFacultiesNameForUniversity(int universityId, String lang);

    /**
     * Finds faculties for university in database.
     *
     * @param universityId An int represents unique university`s id.
     * @param lang         A String represents user`s language.
     * @return List of Faculties data
     */
    List<Faculty> findFacultiesForUniversity(int universityId, String lang);

    /**
     * Finds faculties that university not have in database.
     *
     * @param universityId An int represents unique university`s id.
     * @param lang         A String represents user`s language.
     * @return List of Faculties data
     */
    List<Faculty> findFacultiesForUniversityNotHave(int universityId, String lang);

    /**
     * Finds all faculties in database.
     *
     * @return Optional of List of Faculties data
     */
    Optional<List<Faculty>> findAll();

    /**
     * Finalises faculty in database.
     *
     * @param facultyId An int represents unique faculty`s id.
     * @throws DBException - if a database access error occurs.
     */
    void finalizeFaculty(int facultyId) throws DBException;

    /**
     * Sets faculty for university in database.
     *
     * @param universityId An int represents unique university`s id.
     * @param faculty      A Faculty containing all needed faculty`s data to insert into database.
     * @throws DBException - if assignment did not occur or a database access error occurs.
     */
    void setFacultyForUniversity(int universityId, Faculty faculty) throws DBException;

    /**
     * Updates faculty for university in database.
     *
     * @param universityId An int represents unique university`s id.
     * @param faculty      A Faculty containing all needed faculty`s data to insert into database.
     * @throws DBException - if changes did not occur or a database access error occurs.
     */
    void updateFacultyForUniversity(int universityId, Faculty faculty) throws DBException;

    /**
     * Finds faculties for student in database.
     *
     * @param userId An int represents unique user`s id.
     * @param lang   A String represents user`s language.
     * @return Optional of List of Faculties data
     * @throws SQLException - if a database access error occurs.
     */
    Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang) throws SQLException;

    /**
     * Deletes faculty from university and changed request state linked to this faculty in database.
     *
     * @param universityId An int represents unique university`s id.
     * @param facultyId    An int represents unique faculty`s id.
     * @param state        A State represents state of request linked with this faculty.
     * @throws SQLException - if FacultyNotExistsException is thrown.
     * @throws DBException  - if a database access error occurs.
     */
    void deleteFromUniversity(int universityId, int facultyId, Request.State state) throws DBException, SQLException;

    /**
     * Finds faculties for student in database and sorts them.
     *
     * @param userId An int represents unique user`s id.
     * @param lang   A String represents user`s language.
     * @param sort   A FacultyComparator represents comparator for Faculty.
     * @return Optional of List of Faculties data
     * @throws SQLException - if a database access error occurs.
     */
    Optional<List<Faculty>> findFacultiesForStudent(int userId, String lang, FacultyComparator sort) throws SQLException;
}
