package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.teacher.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TeacherDao extends GenericDao<Teacher> {

    private final SessionFactory sessionFactory;

    public TeacherDao(SessionFactory sessionFactory) {
        super(Teacher.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
