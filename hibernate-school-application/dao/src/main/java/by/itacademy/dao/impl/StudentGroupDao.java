package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.studentGroup.StudentGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StudentGroupDao extends GenericDao<StudentGroup> {

    private final SessionFactory sessionFactory;

    public StudentGroupDao(SessionFactory sessionFactory) {
        super(StudentGroup.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
