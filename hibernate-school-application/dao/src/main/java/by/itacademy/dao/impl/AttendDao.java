package by.itacademy.dao.impl;

import by.itacademy.attend.Attend;
import by.itacademy.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AttendDao extends GenericDao<Attend> {

    private final SessionFactory sessionFactory;

    public AttendDao(SessionFactory sessionFactory) {
        super(Attend.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
