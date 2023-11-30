package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.school.School;
import by.itacademy.subject.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.itacademy.dao.impl.BaseDaoIntegrationTest.SESSION_FACTORY;
import static by.itacademy.dao.impl.SchoolDaoIntegrationTest.SCHOOL_DAO;

public class SubjectDaoIntegrationTest {

    private static final SubjectDao SUBJECT_DAO = new SubjectDao(SESSION_FACTORY);

    private static final Subject SUBJECT = new Subject();

    @BeforeAll
    public static void createObjectSubject() {
        SUBJECT.setName("Graphic design");
    }

    @Test
    void testCreateSubject_happyPath() throws DaoException {
        SUBJECT_DAO.create(SUBJECT);

        Assertions.assertEquals("Graphic design", SUBJECT_DAO.read(2).getName());
    }

    @Test
    void testReadSubject_happyPath() throws DaoException {
        Assertions.assertEquals("Java core", SUBJECT_DAO.read(1).getName());
    }

    @Test
    void testUpdateSubject_happyPath() throws DaoException {
        SUBJECT_DAO.create(SUBJECT);

        SUBJECT.setId(2);
        SUBJECT.setName("Web design");

        SUBJECT_DAO.update(SUBJECT);

        Assertions.assertEquals("Web design", SUBJECT_DAO.read(2).getName());
    }

    @Test
    void testDeleteSubject_happyPath() throws DaoException {
        SUBJECT_DAO.delete(1);

        Assertions.assertNull(SUBJECT_DAO.read(1));
    }

    @Test
    void testSchoolSubjectCreate_happyPath() throws DaoException {
        final School school = SCHOOL_DAO.read(3);
        school.setSubjects(List.of(SUBJECT_DAO.read(1)));
        SCHOOL_DAO.update(school);

        final Subject subject = SUBJECT_DAO.read(1);
        subject.setSchools(List.of(SCHOOL_DAO.read(3)));
        SUBJECT_DAO.update(subject);

        Assertions.assertEquals(3, SUBJECT_DAO.read(1).getSchools().get(0).getId());
        Assertions.assertEquals(1, SCHOOL_DAO.read(3).getSubjects().get(0).getId());
    }
}
