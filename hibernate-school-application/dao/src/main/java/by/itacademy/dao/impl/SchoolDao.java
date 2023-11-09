package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.school.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SchoolDao extends GenericDao<School> {

    private final SessionFactory sessionFactory;

    public SchoolDao(SessionFactory sessionFactory) {
        super(School.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}

