package by.itacademy.assessment;

import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentTest {

    private final Assessment assessment = new Assessment();
    private final static Student expectedStudent = new Student();
    private final static Teacher expectedTeacher = new Teacher();
    private final static Subject expectedSubject = new Subject();
    private final static Integer expectedAssessment = 9;
    private final static OffsetDateTime expectedCreated = OffsetDateTime.now();

    @Test
    void testSetAndGetStudent_happyPath() {
        assessment.setStudent(expectedStudent);

        final Student actualStudent = assessment.getStudent();

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void testSetAndGetTeacher_happyPath() {
        assessment.setTeacher(expectedTeacher);

        final Teacher actualTeacher = assessment.getTeacher();

        assertEquals(expectedTeacher, actualTeacher);
    }

    @Test
    void testSetAndGetSubject_happyPath() {
        assessment.setSubject(expectedSubject);

        final Subject actualSubject = assessment.getSubject();

        assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void testSetAndGetAssessment_happyPath() {
        assessment.setAssessment(9);

        final Integer actualAssessment = assessment.getAssessment();

        assertEquals(expectedAssessment, actualAssessment);
    }

    @Test
    void testSetAndGetCreated_happyPath() {
        assessment.setCreated(expectedCreated);

        final OffsetDateTime actualCreated = assessment.getCreated();

        assertEquals(expectedCreated, actualCreated);
    }

}