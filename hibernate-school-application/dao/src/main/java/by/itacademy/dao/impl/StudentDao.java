package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.student.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StudentDao extends GenericDao<Student> {

    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        super(Student.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
