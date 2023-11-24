package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.parent.Parent;
import org.hibernate.SessionFactory;

public class ParentDao extends GenericDao<Parent> {

    public ParentDao(SessionFactory sessionFactory) {
        super(Parent.class, DaoException.ParentDaoException::new, sessionFactory);
    }

}
