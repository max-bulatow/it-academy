package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.lesson.Lesson;
import org.hibernate.SessionFactory;

public class LessonDao extends GenericDao<Lesson> {

    public LessonDao(SessionFactory sessionFactory) {
        super(Lesson.class, DaoException.LessonDaoException::new, sessionFactory);
    }

}
