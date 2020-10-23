package ua.training.model.dao;

/**
 * GenericDao interface provides five CRUD methods to manipulate with specifying entity.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public interface GenericDao<T> extends AutoCloseable {
//    void create(T entity);

//    T findById(int id);

//    List<T> findAll();

//    void update(T entity);

//    void delete(int id);

    @Override
    void close();
}
