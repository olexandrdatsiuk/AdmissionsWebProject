package ua.training.model.dao;

import ua.training.model.entity.University;

import java.util.List;
import java.util.Optional;

public interface UniversityDao extends GenericDao<UniversityDao> {
    Optional<List<University>> findAll(String lang);
}
