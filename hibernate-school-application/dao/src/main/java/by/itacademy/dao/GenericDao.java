package by.itacademy.dao;

import by.itacademy.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;

public abstract class GenericDao<T> implements Dao<T> {

    private Class<T> entityClass;

    public final void setEntityClass(final Class<T> entityClassToSet) {
        entityClass = Preconditions.checkNotNull(entityClassToSet, "entityClassToSet");
    }

    @Override
    public T create(T entity) throws DaoException {
        Session session = sessionBeginTransaction();
        session.merge(entity);
        session.getTransaction().commit();

        return entity;
    }

    @Override
    public T read(Integer id) throws DaoException {

        return (T) entityClass;
    }

    @Override
    public T update(T entity) throws DaoException {
        Session session = sessionBeginTransaction();
        T entityUpdate = session.merge(entity);
        session.getTransaction().commit();

        return entityUpdate;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    protected static Session sessionBeginTransaction() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        return session;
    }
}
