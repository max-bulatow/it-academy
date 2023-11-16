package by.itacademy.assessment;

import by.itacademy.BaseEntity;
import by.itacademy.lesson.Lesson;
import by.itacademy.student.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "assessment")
public class Assessment extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lesson_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__lesson__id")
    )
    private Lesson lesson;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk__assessment__student__id")
    )
    private Student student;

    @Column(name = "assessment", length = 2)
    private Integer assessment;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(final Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(final Student student) {
        this.student = student;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(final Integer assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessment = " + assessment +
                '}';
    }
}
