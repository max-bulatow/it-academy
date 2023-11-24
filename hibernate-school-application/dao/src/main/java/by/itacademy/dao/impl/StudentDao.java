package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.student.Student;
import org.hibernate.SessionFactory;

public class StudentDao extends GenericDao<Student> {

    public StudentDao(SessionFactory sessionFactory) {
        super(Student.class, DaoException.StudentDaoException::new, sessionFactory);
    }

}
