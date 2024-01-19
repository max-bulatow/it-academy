package by.itacademy.web.controller;

import by.itacademy.web.controller.dto.AssessmentDto;
import by.itacademy.web.controller.dto.StudentDto;
import by.itacademy.web.controller.dto.SubjectDto;
import by.itacademy.web.controller.dto.TeacherDto;
import by.itacademy.web.core.entity.Assessment;
import by.itacademy.web.core.entity.Student;
import by.itacademy.web.core.entity.Subject;
import by.itacademy.web.core.entity.Teacher;
import jakarta.validation.constraints.Min;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TestController {

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping
    public String test() {
        return "test success";
    }

    @GetMapping(path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public StudentDto getStudent(
            @PathVariable(name = "id") @Min(value = 1, message = "Values must be more than 0") final int id
    ) {
        final var student = sessionFactory.getCurrentSession().find(Student.class, id);

        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @GetMapping(path = "/teacher/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TeacherDto getTeacher(
            @PathVariable(name = "id") @Min(value = 1, message = "Values must be more than 0") final int id
    ) {
        final var teacher = sessionFactory.getCurrentSession().find(Teacher.class, id);

        return new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName());
    }

    @GetMapping(path = "/assessment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public AssessmentDto getAssessment(
            @PathVariable(name = "id") @Min(value = 1, message = "Values must be more than 0") final int id
    ) {
        final var assessment = sessionFactory.getCurrentSession().find(Assessment.class, id);

        return new AssessmentDto(assessment.getId(), assessment.getAssessment());
    }

    @GetMapping(path = "/subject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SubjectDto getSubject(
            @PathVariable(name = "id") @Min(value = 1, message = "Values must be more than 0") final int id
    ) {
        final var subject = sessionFactory.getCurrentSession().find(Subject.class, id);

        return new SubjectDto(subject.getId(), subject.getName());
    }

}
