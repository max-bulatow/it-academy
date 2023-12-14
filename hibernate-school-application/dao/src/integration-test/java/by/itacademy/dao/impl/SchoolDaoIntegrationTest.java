package by.itacademy.dao.impl;

import by.itacademy.address.Address;
import by.itacademy.school.School;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import static by.itacademy.dao.impl.AddressDaoIntegrationTest.ADDRESS_DAO;
import static by.itacademy.dao.impl.TestHibernateUtil.SESSION_FACTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SchoolDaoIntegrationTest extends BaseDaoIntegrationTest {

    private Session session;

    protected static SchoolDao SCHOOL_DAO;

    private static final School SCHOOL = new School();

    @BeforeAll
    public static void createDao() {
        SCHOOL_DAO = new SchoolDao(SESSION_FACTORY);
    }

    @BeforeAll
    public static void createObject() throws Exception {
        final Address address = new Address();
        address.setId(2);
        address.setCity("Brest");
        address.setStreet("Dzerjinskogo");
        address.setBuildingNumber("50");

        ADDRESS_DAO.create(address);

        SCHOOL.setId(1);
        SCHOOL.setName("IT Academy");
        SCHOOL.setAddress(ADDRESS_DAO.read(2));
    }

    @BeforeEach
    public void openSession() {
        session = SESSION_FACTORY.openSession();
    }

    @AfterEach
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    @Order(1)
    void testCreateSchool_happyPath() throws Exception {
        SCHOOL_DAO.create(SCHOOL);

        final ResultSetVerifier verifier = (rs) -> {
            Integer schoolId = rs.getInt("id");
            String name = rs.getString("name");
            Integer addressId = rs.getInt("address_id");

            Assertions.assertEquals(SCHOOL.getId(), schoolId);
            Assertions.assertEquals(SCHOOL.getName(), name);
            Assertions.assertEquals(SCHOOL.getAddress().getId(), addressId);
        };

        verifyCreatedRow("school", SCHOOL.getId(), verifier);
    }

    @Test
    @Order(2)
    void testReadSchool_happyPath() throws Exception {
        final School readSchool = new School();
        readSchool.setId(SCHOOL_DAO.read(1).getId());
        readSchool.setName(SCHOOL_DAO.read(1).getName());
        readSchool.setAddress(SCHOOL_DAO.read(1).getAddress());

        final ResultSetVerifier verifier = (rs) -> {
            Integer schoolId = rs.getInt("id");
            String name = rs.getString("name");
            Integer addressId = rs.getInt("address_id");

            Assertions.assertEquals(readSchool.getId(), schoolId);
            Assertions.assertEquals(readSchool.getName(), name);
            Assertions.assertEquals(readSchool.getAddress().getId(), addressId);
        };

        verifyCreatedRow("school", SCHOOL.getId(), verifier);
    }

    @Test
    @Order(3)
    void testUpdateSchool_happyPath() throws Exception {
        final School updateSchool = new School();
        updateSchool.setId(1);
        updateSchool.setName("IT Academy Brest");
        updateSchool.setAddress(ADDRESS_DAO.read(2));

        SCHOOL_DAO.update(updateSchool);

        final ResultSetVerifier verifier = (rs) -> {
            Integer schoolId = rs.getInt("id");
            String name = rs.getString("name");
            Integer addressId = rs.getInt("address_id");

            Assertions.assertEquals(updateSchool.getId(), schoolId);
            Assertions.assertEquals(updateSchool.getName(), name);
            Assertions.assertEquals(updateSchool.getAddress().getId(), addressId);
        };

        verifyCreatedRow("school", updateSchool.getId(), verifier);
    }

    @Test
    @Order(4)
    void testDeleteSchool_happyPath() throws Exception {
        SCHOOL_DAO.delete(1);

        final ResultSetVerifier verifier = (rs) -> {
            Integer schoolID = rs.getInt("id");

            Assertions.assertNull(schoolID);
        };
    }

}
