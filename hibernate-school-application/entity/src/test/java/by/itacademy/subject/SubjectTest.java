package by.itacademy.subject;

import by.itacademy.assessment.Assessment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {

    private final Subject subject = new Subject();
    private final static String expectedName = "Biology";
    private final List<Assessment> expectedAssessments = new ArrayList<>();
    @Test
    void testSetAndGetName_happyPath() {
        subject.setName(expectedName);

        final String actualName = subject.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void testSetAndGetAssessments_happyPath() {
        subject.setAssessments(expectedAssessments);

        final List<Assessment> actualAssessment = subject.getAssessments();

        assertEquals(expectedAssessments, actualAssessment);
    }

}