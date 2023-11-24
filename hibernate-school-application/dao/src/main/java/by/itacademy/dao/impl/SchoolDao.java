package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.school.School;
import org.hibernate.SessionFactory;

public class SchoolDao extends GenericDao<School> {

    public SchoolDao(SessionFactory sessionFactory) {
        super(School.class, DaoException.SchoolDaoException::new, sessionFactory);
    }

}

