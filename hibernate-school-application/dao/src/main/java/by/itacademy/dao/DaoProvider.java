package by.itacademy.dao;

import jakarta.persistence.PersistenceException;

public interface DaoProvider extends AutoCloseable{

    <T> GenericDao<T> provide(Class<T> entityClass);

    @Override
    void close() throws PersistenceException;
}
