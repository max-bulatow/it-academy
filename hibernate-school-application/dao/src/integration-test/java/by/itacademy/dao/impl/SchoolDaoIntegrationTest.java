package by.itacademy.dao.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.school.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static by.itacademy.dao.impl.AddressDaoIntegrationTest.ADDRESS_DAO;
import static by.itacademy.dao.impl.BaseDaoIntegrationTest.SESSION_FACTORY;

public class SchoolDaoIntegrationTest {

    protected static final SchoolDao SCHOOL_DAO = new SchoolDao(SESSION_FACTORY);

    private static final School SCHOOL = new School();

    @BeforeAll
    public static void createObjectSchool() throws DaoException {
        SCHOOL.setName("IT-Academy Junior");
        SCHOOL.setAddress(ADDRESS_DAO.read(2));
    }

    @Test
    void testCreateSchool_happyPath() throws DaoException {
        SCHOOL_DAO.create(SCHOOL);

        Assertions.assertEquals("IT-Academy Brest", SCHOOL_DAO.read(3).getName());
        Assertions.assertEquals(2, SCHOOL_DAO.read(3).getAddress().getId());
    }

    @Test
    void testReadSchool_happyPath() throws DaoException {
        Assertions.assertEquals("IT-Academy Brest", SCHOOL_DAO.read(3).getName());
        Assertions.assertEquals(2, SCHOOL_DAO.read(3).getAddress().getId());
        Assertions.assertEquals("Brest", SCHOOL_DAO.read(3).getAddress().getCity());
        Assertions.assertEquals("Masherova", SCHOOL_DAO.read(3).getAddress().getStreet());
        Assertions.assertEquals("30", SCHOOL_DAO.read(3).getAddress().getBuildingNumber());
    }

    @Test
    void testUpdateSchool_happyPath() throws DaoException {
        SCHOOL_DAO.create(SCHOOL);

        SCHOOL.setId(3);
        SCHOOL.setName("IT-Academy Brest");
        SCHOOL.setAddress(ADDRESS_DAO.read(2));

        SCHOOL_DAO.update(SCHOOL);

        Assertions.assertEquals("IT-Academy Brest", SCHOOL_DAO.read(3).getName());
        Assertions.assertEquals(2, SCHOOL_DAO.read(3).getAddress().getId());
    }

    @Test
    void testDeleteSchool_happyPath() throws DaoException {
        SCHOOL_DAO.delete(1);

        Assertions.assertNull(SCHOOL_DAO.read(1));
    }
}
