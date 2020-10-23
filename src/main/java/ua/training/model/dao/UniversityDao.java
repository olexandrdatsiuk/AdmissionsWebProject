package ua.training.model.dao;

import ua.training.model.entity.University;

import java.util.List;
import java.util.Optional;

/**
 * UniversityDao interface provides one method to manipulate with University entity.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public interface UniversityDao extends GenericDao<UniversityDao> {
    /**
     * Finds all universities in database.
     *
     * @param lang A String represents user`s language.
     * @return An Optional representing List of University data.
     */
    Optional<List<University>> findAll(String lang);
}
