package by.itacademy.dao.impl;

import by.itacademy.attend.Attend;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import org.hibernate.SessionFactory;

public class AttendDao extends GenericDao<Attend> {

    public AttendDao(SessionFactory sessionFactory) {
        super(Attend.class, DaoException.AttendDaoException::new, sessionFactory);
    }

}
