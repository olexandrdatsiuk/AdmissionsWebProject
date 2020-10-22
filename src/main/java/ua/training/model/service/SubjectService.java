package ua.training.model.service;

import ua.training.exception.db.DBException;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SubjectDao;
import ua.training.model.entity.Subject;

import java.util.List;
import java.util.Optional;

public class SubjectService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<List<Subject>> findSubjectsUserNotHave(int userId, String lang) throws DBException {
        try (SubjectDao subjectDao = daoFactory.createSubjectDao()) {
            return subjectDao.findSubjectsUserNotHave(userId, lang);
        }
    }

    public Optional<List<Subject>> getSubjectsNames(String lang) throws DBException {
        try (SubjectDao subjectDao = daoFactory.createSubjectDao()) {
            return subjectDao.getSubjectsNames(lang);
        }
    }

    public void setSubjectForUser(Subject s) throws DBException {
        try (SubjectDao subjectDao = daoFactory.createSubjectDao()) {
            subjectDao.setSubjectForUser(s);
        }
    }

    public Optional<List<Subject>> findSubjectsForUser(int userId, String lang) throws DBException {
        try (SubjectDao subjectDao = daoFactory.createSubjectDao()) {
            return subjectDao.findSubjectsForUser(userId, lang);
        }
    }

}
