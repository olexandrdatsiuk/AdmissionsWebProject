package ua.training.model.dao;

import ua.training.exception.db.DBException;
import ua.training.model.entity.User;
import ua.training.model.enumeration.UserStatus;

import java.sql.SQLException;
import java.util.Optional;

/**
 * UserDao interface provides five methods to manipulate with User entity.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public interface UserDao extends GenericDao<UserDao> {
    /**
     * Finds User in database.
     *
     * @param email A String containing user`s email.
     * @param pass  A String containing user`s password.
     * @return A User representing user`s data.
     * @throws DBException - if UserNotExistsException or SQLException is thrown.
     */
    User loginByEmailAndPass(String email, String pass) throws DBException;

    /**
     * Creates User in database.
     *
     * @param user A User containing all needed user`s data to insert into database.
     * @throws SQLException - if a database access error occurs.
     * @throws DBException  - if a database already contains an unique element.
     */
    void create(User user) throws DBException, SQLException;

    /**
     * Finds account details in database.
     *
     * @param id An int represents unique user`s id.
     * @return A Optional representing User.
     */
    Optional<User> findAccountDetails(int id);

    /**
     * Finds study account details in database.
     *
     * @param userId An int represents unique user`s id.
     * @param lang   A String represents user`s language.
     * @return A Optional representing User.
     */
    Optional<User> findStudyAccountDetails(int userId, String lang);

    /**
     * Changes User`s status in database.
     *
     * @param email  A String represents user`s email.
     * @param status A UserStatus represents user status.
     * @param userId An int represents unique user`s id.
     * @throws SQLException - if a database access error occurs
     * @throws DBException  - if a database already contains an unique element.
     */
    void changeStatus(String email, UserStatus status, int userId) throws DBException;

}
