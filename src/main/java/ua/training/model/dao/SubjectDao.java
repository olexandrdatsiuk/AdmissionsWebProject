package ua.training.model.dao;

import ua.training.exception.db.DBException;
import ua.training.model.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectDao extends GenericDao<SubjectDao> {
    Optional<List<Subject>> findSubjectsUserNotHave(int userId, String lang);

    void setSubjectForUser(Subject subject) throws DBException;

    Optional<List<Subject>> findSubjectsForUser(int userId, String lang);

    Optional<List<Subject>> getSubjectsNames(String lang);
}
