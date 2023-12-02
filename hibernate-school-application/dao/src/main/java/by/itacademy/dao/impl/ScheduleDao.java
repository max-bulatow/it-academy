package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.schedule.Schedule;
import org.hibernate.SessionFactory;

public class ScheduleDao extends GenericDao<Schedule> {

    public ScheduleDao(final SessionFactory sessionFactory) {
        super(Schedule.class, DaoException.ScheduleDaoException::new, sessionFactory);
    }

}
