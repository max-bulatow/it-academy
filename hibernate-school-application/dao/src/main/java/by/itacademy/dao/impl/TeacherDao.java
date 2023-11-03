package by.itacademy.dao.impl;

import by.itacademy.dao.Dao;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.teacher.Teacher;
import org.hibernate.Session;

public class TeacherDao extends GenericDao<Teacher> implements Dao<Teacher> {

    public TeacherDao() {
        setEntityClass(Teacher.class);
    }

    @Override
    public Teacher read(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Teacher teacherFind = session.find(Teacher.class, id);
        session.getTransaction().commit();

        return teacherFind;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Teacher teacherDelete = session.find(Teacher.class, id);
        session.remove(teacherDelete);
        session.getTransaction().commit();
    }

}
