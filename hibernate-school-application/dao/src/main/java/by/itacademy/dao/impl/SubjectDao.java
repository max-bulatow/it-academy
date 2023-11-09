package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.subject.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SubjectDao extends GenericDao<Subject> {

    private final SessionFactory sessionFactory;

    public SubjectDao(SessionFactory sessionFactory) {
        super(Subject.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
