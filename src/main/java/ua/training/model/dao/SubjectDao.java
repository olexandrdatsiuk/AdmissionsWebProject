package ua.training.model.dao;

import ua.training.exception.db.DBException;
import ua.training.model.entity.Subject;

import java.util.List;
import java.util.Optional;

/**
 * SubjectDao interface provides four methods to manipulate with Subject entity.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public interface SubjectDao extends GenericDao<SubjectDao> {
    /**
     * Finds Subjects in database that user not have.
     *
     * @param userId An int represents unique user`s id.
     * @param lang   A String represents user`s language.
     * @return An Optional of List of Subjects data.
     */
    Optional<List<Subject>> findSubjectsUserNotHave(int userId, String lang);

    /**
     * Finds User in database.
     *
     * @param subject A Subject containing subject`s data.
     * @throws DBException - if UserNotExistsException is thrown or database access error occurs.
     */
    void setSubjectForUser(Subject subject) throws DBException;

    /**
     * Finds Subjects in database that user have.
     *
     * @param userId An int represents unique user`s id.
     * @param lang   A String represents user`s language.
     * @return An Optional of List of Subjects data.
     */
    Optional<List<Subject>> findSubjectsForUser(int userId, String lang);

    /**
     * Finds Subjects names in database.
     *
     * @param lang A String represents user`s language.
     * @return An Optional of List of Subjects data.
     */
    Optional<List<Subject>> getSubjectsNames(String lang);
}
