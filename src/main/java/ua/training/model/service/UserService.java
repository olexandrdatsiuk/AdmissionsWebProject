package ua.training.model.service;

import ua.training.exception.db.DBException;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.enumeration.UserStatus;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public User loginByEmailAndPass(String email, String pass) throws DBException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.loginByEmailAndPass(email, pass);
        }
    }

    public void create(User user) throws DBException, SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }

    public Optional<User> findAccountDetails(int userId) throws DBException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAccountDetails(userId);
        }
    }

    public Optional<User> findStudyAccountDetails(int userId, String lang) throws DBException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findStudyAccountDetails(userId, lang);
        }
    }

    public void changeStatus(String email, UserStatus status, int userId) throws DBException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.changeStatus(email, status, userId);
        }
    }
}
