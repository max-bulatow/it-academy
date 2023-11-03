package by.itacademy.dao;

public interface Dao<T> {
    T create(T entity) throws DaoException;

    T read(Integer id) throws DaoException;

    T update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;

}
