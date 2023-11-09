package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.lesson.Lesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LessonDao extends GenericDao<Lesson> {

    private final SessionFactory sessionFactory;

    public LessonDao(SessionFactory sessionFactory) {
        super(Lesson.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
