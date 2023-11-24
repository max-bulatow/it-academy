package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.subject.Subject;
import org.hibernate.SessionFactory;

public class SubjectDao extends GenericDao<Subject> {

    public SubjectDao(SessionFactory sessionFactory) {
        super(Subject.class, DaoException.SubjectDaoException::new, sessionFactory);
    }

}
