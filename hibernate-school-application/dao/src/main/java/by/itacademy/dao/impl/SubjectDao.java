package by.itacademy.dao.impl;

import by.itacademy.dao.Dao;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.subject.Subject;
import org.hibernate.Session;

public class SubjectDao extends GenericDao<Subject> implements Dao<Subject> {

    public SubjectDao() {
        setEntityClass(Subject.class);
    }

    @Override
    public Subject read(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Subject subjectFind = session.find(Subject.class, id);
        session.getTransaction().commit();

        return subjectFind;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Subject subjectDelete = session.find(Subject.class, id);
        session.remove(subjectDelete);
        session.getTransaction().commit();
    }

}
