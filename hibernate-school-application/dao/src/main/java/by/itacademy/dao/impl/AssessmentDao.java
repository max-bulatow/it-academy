package by.itacademy.dao.impl;

import by.itacademy.assessment.Assessment;
import by.itacademy.dao.Dao;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import org.hibernate.Session;

public class AssessmentDao extends GenericDao<Assessment> implements Dao<Assessment> {

    public AssessmentDao() {
        setEntityClass(Assessment.class);
    }

    @Override
    public Assessment read(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Assessment assessmentFind = session.find(Assessment.class, id);
        session.getTransaction().commit();

        return assessmentFind;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Session session = sessionBeginTransaction();
        Assessment assessmentDelete = session.find(Assessment.class, id);
        session.remove(assessmentDelete);
        session.getTransaction().commit();
    }

}
