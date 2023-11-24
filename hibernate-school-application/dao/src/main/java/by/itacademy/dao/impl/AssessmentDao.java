package by.itacademy.dao.impl;

import by.itacademy.assessment.Assessment;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import org.hibernate.SessionFactory;

public class AssessmentDao extends GenericDao<Assessment> {

    public AssessmentDao(SessionFactory sessionFactory) {
        super(Assessment.class, DaoException.AssessmentDaoException::new, sessionFactory);
    }

}
