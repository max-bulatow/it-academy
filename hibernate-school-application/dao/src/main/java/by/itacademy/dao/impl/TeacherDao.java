package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.teacher.Teacher;
import org.hibernate.SessionFactory;

public class TeacherDao extends GenericDao<Teacher> {

    public TeacherDao(final SessionFactory sessionFactory) {
        super(Teacher.class, DaoException.TeacherDaoException::new, sessionFactory);
    }

}
