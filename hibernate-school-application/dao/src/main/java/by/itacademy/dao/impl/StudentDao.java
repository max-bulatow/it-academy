package by.itacademy.dao.impl;

import by.itacademy.dao.Dao;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import by.itacademy.student.Student;
import org.hibernate.Session;

public class StudentDao extends GenericDao<Student> implements Dao<Student> {

    public StudentDao() {
        setEntityClass(Student.class);
    }

    @Override
    public Student read(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Student studentFind = session.find(Student.class, id);
        session.getTransaction().commit();

        return studentFind;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Student studentDelete = session.find(Student.class, id);
        session.remove(studentDelete);
        session.getTransaction().commit();
    }

}
