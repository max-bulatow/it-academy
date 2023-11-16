package by.itacademy.dao;

public interface Dao<T> {

    void create(T entity);

    T read(Class<T> clazz, Integer id);

    void update(T entity);

    void delete(Integer id);
}
