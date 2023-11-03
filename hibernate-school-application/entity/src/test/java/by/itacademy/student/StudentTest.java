package by.itacademy.student;

import by.itacademy.assessment.Assessment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    private final Student student = new Student();

    private final static List<Assessment> expectedAssessment = new ArrayList<>();

    @Test
    void testSetAndGetAssessments_happyPath() {
        student.setAssessments(expectedAssessment);

        final List<Assessment> actualAssessment = student.getAssessments();

        assertEquals(expectedAssessment, actualAssessment);

    }

}