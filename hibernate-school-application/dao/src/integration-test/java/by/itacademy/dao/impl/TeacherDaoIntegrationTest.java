package by.itacademy.dao.impl;

import by.itacademy.school.School;
import by.itacademy.teacher.Teacher;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

import static by.itacademy.dao.impl.AddressDaoIntegrationTest.ADDRESS_DAO;
import static by.itacademy.dao.impl.SchoolDaoIntegrationTest.SCHOOL_DAO;
import static by.itacademy.dao.impl.TestHibernateUtil.SESSION_FACTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherDaoIntegrationTest extends BaseDaoIntegrationTest {

    private Session session;

    private static TeacherDao TEACHER_DAO;

    private static final Teacher TEACHER = new Teacher();

    @BeforeAll
    public static void createDao() {
        TEACHER_DAO = new TeacherDao(SESSION_FACTORY);
    }

    @BeforeAll
    public static void createObject() {
        TEACHER.setId(1);
        TEACHER.setFirstName("Ivan");
        TEACHER.setLastName("Ivanov");
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
    void testCreateTeacher_happyPath() throws Exception {
        TEACHER_DAO.create(TEACHER);

        final ResultSetVerifier verifier = (rs) -> {
            Integer teacherId = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");

            Assertions.assertEquals(TEACHER.getId(), teacherId);
            Assertions.assertEquals(TEACHER.getFirstName(), firstName);
            Assertions.assertEquals(TEACHER.getLastName(), lastName);
        };

        verifyCreatedRow("teacher", TEACHER.getId(), verifier);
    }

    @Test
    @Order(2)
    void testReadTeacher_happyPath() throws Exception {
        final Teacher readTeacher = new Teacher();
        readTeacher.setId(TEACHER_DAO.read(1).getId());
        readTeacher.setFirstName(TEACHER_DAO.read(1).getFirstName());
        readTeacher.setLastName(TEACHER_DAO.read(1).getLastName());

        final ResultSetVerifier verifier = (rs) -> {
            Integer teacherId = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");

            Assertions.assertEquals(readTeacher.getId(), teacherId);
            Assertions.assertEquals(readTeacher.getFirstName(), firstName);
            Assertions.assertEquals(readTeacher.getLastName(), lastName);
        };

        verifyCreatedRow("teacher", TEACHER.getId(), verifier);
    }

    @Test
    @Order(3)
    void testUpdateTeacher_happyPath() throws Exception {
        final Teacher updateTeacher = new Teacher();
        updateTeacher.setId(1);
        updateTeacher.setFirstName("Petr");
        updateTeacher.setLastName("Petrov");

        TEACHER_DAO.update(updateTeacher);

        final ResultSetVerifier verifier = (rs) -> {
            Integer teacherId = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");

            Assertions.assertEquals(updateTeacher.getId(), teacherId);
            Assertions.assertEquals(updateTeacher.getFirstName(), firstName);
            Assertions.assertEquals(updateTeacher.getLastName(), lastName);
        };

        verifyCreatedRow("teacher", updateTeacher.getId(), verifier);
    }

    @Test
    @Order(4)
    void testDeleteTeacher_happyPath() throws Exception {
        TEACHER_DAO.delete(1);

        final ResultSetVerifier verifier = (rs) -> {
            Integer teacherID = rs.getInt("id");

            Assertions.assertNull(teacherID);
        };
    }

    @Test
    @Order(5)
    void testCreateSchoolTeacher_happyPath() throws Exception {
        final Teacher teacher = new Teacher();
        final School school = new School();

        teacher.setId(2);
        teacher.setFirstName("Ivan");
        teacher.setLastName("Petrov");
        teacher.setSchools(List.of(school));

        school.setId(2);
        school.setName("IT Academy Teen");
        school.setAddress(ADDRESS_DAO.read(3));
        school.setTeachers(List.of(teacher));

        SCHOOL_DAO.update(school);

        TEACHER_DAO.create(teacher);

        final ResultSetVerifier verifier = (rs) -> {
            Integer schoolId = rs.getInt("school_id");
            Integer teacherId = rs.getInt("teacher_id");

            Assertions.assertEquals(school.getId(), schoolId);
            Assertions.assertEquals(teacher.getId(), teacherId);
        };

        verifyCreatedRow("school_teacher", school.getId(), verifier);
        verifyCreatedRow("school_teacher", teacher.getId(), verifier);
    }
}
