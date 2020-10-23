package ua.training.model.dao;

import ua.training.model.entity.Request;

import java.util.List;
import java.util.Optional;

/**
 * RequestDao interface provides four methods to manipulate with Request entity.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public interface RequestDao extends GenericDao<RequestDao> {
    /**
     * Creates Request in database.
     *
     * @param userId    An int represents unique user`s id.
     * @param facultyId An int represents unique faculty`s id.
     * @param lang      A String represents user`s language.
     */
    void create(int userId, int facultyId, String lang);

    /**
     * Finds all requests for user in database.
     *
     * @param userId An int represents unique user`s id.
     * @param lang   A String represents user`s language.
     * @return An Optional representing List of Requests data.
     */
    Optional<List<Request>> findForUser(int userId, String lang);

    /**
     * Finds all requests in database.
     *
     * @param startFrom An int represents value from which record get data.
     * @param lang      A String represents user`s language.
     * @return An Optional representing List of Requests data.
     */
    Optional<List<Request>> findAll(int startFrom, String lang);

    /**
     * Changes request status of specifying user in database.
     *
     * @param userId    An int represents unique user`s id.
     * @param facultyId An int represents unique faculty`s id.
     * @param state     An int represents state of request.
     */
    void changeRequestStatus(int userId, int facultyId, int state);
}
