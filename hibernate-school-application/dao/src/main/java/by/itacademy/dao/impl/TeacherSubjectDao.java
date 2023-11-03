package by.itacademy.dao.impl;

import by.itacademy.TeacherSubject;
import by.itacademy.dao.Dao;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import org.hibernate.Session;

public class TeacherSubjectDao extends GenericDao<TeacherSubject> implements Dao<TeacherSubject> {

    public TeacherSubjectDao() {
        setEntityClass(TeacherSubject.class);
    }

    @Override
    public TeacherSubject read(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        TeacherSubject teacherSubjectFind = session.find(TeacherSubject.class, id);
        session.getTransaction().commit();

        return teacherSubjectFind;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        TeacherSubject teacherSubjectDelete = session.find(TeacherSubject.class, id);
        session.remove(teacherSubjectDelete);
        session.getTransaction().commit();
    }
}
