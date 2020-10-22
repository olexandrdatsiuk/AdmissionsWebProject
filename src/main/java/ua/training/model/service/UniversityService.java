package ua.training.model.service;

import ua.training.exception.db.DBException;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UniversityDao;
import ua.training.model.entity.University;

import java.util.List;
import java.util.Optional;

public class UniversityService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<List<University>> findAll(String lang) throws DBException {
        try (UniversityDao universityDao = daoFactory.createUniversityDao()) {
            return universityDao.findAll(lang);
        }
    }

}
