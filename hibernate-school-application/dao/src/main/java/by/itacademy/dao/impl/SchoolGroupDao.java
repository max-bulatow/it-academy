package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.group.SchoolGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SchoolGroupDao extends GenericDao<SchoolGroup> {

    private final SessionFactory sessionFactory;

    public SchoolGroupDao(SessionFactory sessionFactory) {
        super(SchoolGroup.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
