package by.itacademy.dao.impl;

import by.itacademy.assessment.Assessment;
import by.itacademy.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AssessmentDao extends GenericDao<Assessment> {

    private final SessionFactory sessionFactory;

    public AssessmentDao(SessionFactory sessionFactory) {
        super(Assessment.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
