package by.itacademy.dao.impl;

import by.itacademy.address.Address;
import by.itacademy.school.School;
import by.itacademy.subject.Subject;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

import static by.itacademy.dao.impl.AddressDaoIntegrationTest.ADDRESS_DAO;
import static by.itacademy.dao.impl.SchoolDaoIntegrationTest.SCHOOL_DAO;
import static by.itacademy.dao.impl.TestHibernateUtil.SESSION_FACTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubjectDaoIntegrationTest extends BaseDaoIntegrationTest {

    private Session session;

    private static SubjectDao SUBJECT_DAO;

    private static final Subject SUBJECT = new Subject();

    @BeforeAll
    public static void createDao() {
        SUBJECT_DAO = new SubjectDao(SESSION_FACTORY);
    }

    @BeforeAll
    public static void createObject() {
        SUBJECT.setId(1);
        SUBJECT.setName("Java core");
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
    void testCreateSubject_happyPath() throws Exception {
        SUBJECT_DAO.create(SUBJECT);

        final ResultSetVerifier verifier = (rs) -> {
            Integer subjectId = rs.getInt("id");
            String name = rs.getString("name");

            Assertions.assertEquals(SUBJECT.getId(), subjectId);
            Assertions.assertEquals(SUBJECT.getName(), name);
        };

        verifyCreatedRow("subject", SUBJECT.getId(), verifier);
    }

    @Test
    @Order(2)
    void testReadSubject_happyPath() throws Exception {
        final Subject readSubject = new Subject();
        readSubject.setId(SUBJECT_DAO.read(1).getId());
        readSubject.setName(SUBJECT_DAO.read(1).getName());

        final ResultSetVerifier verifier = (rs) -> {
            Integer subjectId = rs.getInt("id");
            String name = rs.getString("name");

            Assertions.assertEquals(readSubject.getId(), subjectId);
            Assertions.assertEquals(readSubject.getName(), name);
        };

        verifyCreatedRow("subject", SUBJECT.getId(), verifier);
    }

    @Test
    @Order(3)
    void testUpdateSubject_happyPath() throws Exception {
        final Subject updateSubject = new Subject();
        updateSubject.setId(1);
        updateSubject.setName("Python core");

        SUBJECT_DAO.update(updateSubject);

        final ResultSetVerifier verifier = (rs) -> {
            Integer subjectId = rs.getInt("id");
            String name = rs.getString("name");

            Assertions.assertEquals(updateSubject.getId(), subjectId);
            Assertions.assertEquals(updateSubject.getName(), name);
        };

        verifyCreatedRow("subject", updateSubject.getId(), verifier);
    }

    @Test
    @Order(4)
    void testDeleteSubject_happyPath() throws Exception {
        SUBJECT_DAO.delete(1);

        final ResultSetVerifier verifier = (rs) -> {
            Integer subjectId = rs.getInt("id");

            Assertions.assertNull(subjectId);
        };
    }

    @Test
    @Order(5)
    void testCreateSchoolSubject_happyPath() throws Exception {
        final Address address = new Address();
        final Subject subject = new Subject();
        final School school = new School();

        address.setId(3);
        address.setCity("Brest");
        address.setStreet("Dzerjinskogo");
        address.setBuildingNumber("50");

        ADDRESS_DAO.create(address);

        subject.setId(2);
        subject.setName("Graphic design");
        subject.setSchools(List.of(school));

        school.setId(2);
        school.setName("IT Academy Teen");
        school.setAddress(ADDRESS_DAO.read(3));
        school.setSubjects(List.of(subject));

        SCHOOL_DAO.create(school);

        SUBJECT_DAO.create(subject);

        final ResultSetVerifier verifier = (rs) -> {
            Integer schoolId = rs.getInt("school_id");
            Integer subjectId = rs.getInt("subject_id");

            Assertions.assertEquals(school.getId(), schoolId);
            Assertions.assertEquals(subject.getId(), subjectId);
        };

        verifyCreatedRow("school_subject", school.getId(), verifier);
        verifyCreatedRow("school_subject", subject.getId(), verifier);
    }

}
