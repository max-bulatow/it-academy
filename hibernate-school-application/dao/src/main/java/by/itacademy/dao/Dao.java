package by.itacademy.dao;

public interface Dao<T> {

    void create(T entity) throws DaoException;

    T read(Integer id) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
