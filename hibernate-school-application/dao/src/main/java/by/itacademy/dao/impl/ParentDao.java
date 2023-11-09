package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.parent.Parent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ParentDao extends GenericDao<Parent> {

    private final SessionFactory sessionFactory;

    public ParentDao(SessionFactory sessionFactory) {
        super(Parent.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
