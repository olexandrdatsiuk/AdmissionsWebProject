package ua.training.model.dao;

import ua.training.model.entity.Request;

import java.util.List;
import java.util.Optional;

public interface RequestDao extends GenericDao<RequestDao> {
    void create(int userId, int facultyId, String lang);

    Optional<List<Request>> findForUser(int userId, String lang);

    Optional<List<Request>> findAll(int startFrom, String lang);

    void changeRequestStatus(int userId, int facultyId, int state);
}
