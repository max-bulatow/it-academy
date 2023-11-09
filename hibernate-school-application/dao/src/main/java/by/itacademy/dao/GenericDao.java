package by.itacademy.dao;

import by.itacademy.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;

public abstract class GenericDao<T> implements Dao<T> {

    private final Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public T create(T entity) throws DaoException {
        Session session = getSession();
        session.beginTransaction();

        session.merge(entity);
        session.getTransaction().commit();

        session.close();

        return entity;
    }

    @Override
    public T read(Integer id) throws DaoException {
        Session session = getSession();
        session.beginTransaction();
        T entity = session.find(type, id);

        session.close();

        return entity;
    }

    @Override
    public void update(T entity) throws DaoException {
        Session session = getSession();
        session.beginTransaction();

        session.merge(entity);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Session session = getSession();
        session.beginTransaction();

        T entity = session.find(type, id);
        session.remove(entity);
        session.getTransaction().commit();

        session.close();
    }

    protected abstract Session getSession();
}
