package by.itacademy;

import by.itacademy.address.Address;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.impl.AddressDao;
import by.itacademy.dao.impl.SchoolDao;
import by.itacademy.school.School;
import by.itacademy.sessionfactory.HibernateUtil;
import org.hibernate.SessionFactory;

public class HibernateDaoApplication {
    public static void main(final String[] args) throws DaoException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        final AddressDao addressDao = new AddressDao(sessionFactory);

        final SchoolDao schoolDao = new SchoolDao(sessionFactory);

    }
}

