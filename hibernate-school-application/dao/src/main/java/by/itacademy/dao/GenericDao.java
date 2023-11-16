package by.itacademy.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class GenericDao<T> implements Dao<T> {

    private final Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Can't create entity:" + entity + ex);
        }
    }

    @Override
    public T read(Class<T> clazz, Integer id) {
        Transaction transaction = null;
        T entity = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            entity = session.find(clazz, id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Can't read entity: " + clazz + ex);
            }
        }
        return entity;
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Can't update entity: " + entity + ex);
        }
    }

    @Override
    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            T entity = session.find(type, id);
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("can't delete entity with id: " + id + ex);
        }
    }

    protected abstract Session getSession();
}
