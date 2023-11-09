package by.itacademy.assessment;

import by.itacademy.BaseEntity;
import by.itacademy.lesson.Lesson;
import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "assessment")
public class Assessment extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__student__id")
    )
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__teacher__id")
    )
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "subject_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__subject__id")
    )
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lesson_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__lesson__id")
    )
    private Lesson lesson;

    @Column(name = "assessment", length = 2)
    private Integer assessment;

    @Column(name = "created", nullable = false)
    private OffsetDateTime created;

    public Student getStudent() {
        return student;
    }

    public void setStudent(final Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(final Subject subject) {
        this.subject = subject;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(final Integer assessment) {
        this.assessment = assessment;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(final OffsetDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "Id = " + super.getId() +
                ", assessment = " + assessment +
                ", created = " + created +
                ", teacher = {teacher id = " + getTeacher().getId() +
                ", teacher first name = " + getTeacher().getFirstName() +
                ", teacher last name = " + getTeacher().getLastName() + " }" +
                ", student = {student id = " + getStudent().getId() +
                ", student first name = " + getStudent().getFirstName() +
                ", student last name = " + getStudent().getLastName() + " }" +
                ", subject = {subject id = " + getSubject().getId() +
                ", subject name = " + getSubject().getName() +
                '}';
    }
}
