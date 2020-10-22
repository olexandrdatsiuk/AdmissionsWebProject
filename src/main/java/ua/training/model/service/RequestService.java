package ua.training.model.service;

import ua.training.exception.db.DBException;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.RequestDao;
import ua.training.model.entity.Request;

import java.util.List;
import java.util.Optional;

public class RequestService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(int userId, int facultyId, String lang) throws DBException {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {
            requestDao.create(userId, facultyId, lang);
        }
    }

    public Optional<List<Request>> findForUser(int userId, String lang) throws DBException {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {
            return requestDao.findForUser(userId, lang);
        }
    }

    public Optional<List<Request>> findAll(int startFrom, String lang) throws DBException {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {
            return requestDao.findAll(startFrom, lang);
        }
    }

    public void changeRequestStatus(int userId, int facultyId, int state) throws DBException {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {
            requestDao.changeRequestStatus(userId, facultyId, state);
        }
    }
}
