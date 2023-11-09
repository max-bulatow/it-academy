package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.schedule.Schedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ScheduleDao extends GenericDao<Schedule> {

    private final SessionFactory sessionFactory;

    public ScheduleDao(SessionFactory sessionFactory) {
        super(Schedule.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
