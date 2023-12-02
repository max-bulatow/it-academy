package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.studentGroup.StudentGroup;
import org.hibernate.SessionFactory;

public class StudentGroupDao extends GenericDao<StudentGroup> {

    public StudentGroupDao(final SessionFactory sessionFactory) {
        super(StudentGroup.class, DaoException.StudentGroupDaoException::new, sessionFactory);
    }

}
