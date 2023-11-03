package by.itacademy.teacher;

import by.itacademy.assessment.Assessment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    private final Teacher teacher = new Teacher();
    private final List<Assessment> expectedAssessments = new ArrayList<>();

    @Test
    void testSetAndGetAssessments_happyPath() {
        teacher.setAssessments(expectedAssessments);

        final List<Assessment> actualAssessment = teacher.getAssessments();

        assertEquals(expectedAssessments, actualAssessment);
    }

}