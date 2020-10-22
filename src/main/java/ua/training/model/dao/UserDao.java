package ua.training.model.dao;

import ua.training.exception.db.DBException;
import ua.training.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends GenericDao<UserDao> {
    User loginByEmailAndPass(String email, String pass) throws DBException;

    void create(User user) throws DBException, SQLException;

    Optional<User> findAccountDetails(int id);

    Optional<User> findStudyAccountDetails(int userId, String lang);

    void changeStatus(String email, User.Status status, int userId) throws DBException;

}
