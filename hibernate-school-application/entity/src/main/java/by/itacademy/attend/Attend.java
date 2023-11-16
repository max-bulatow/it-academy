package by.itacademy.attend;

import by.itacademy.BaseEntity;
import by.itacademy.lesson.Lesson;
import by.itacademy.student.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "attend")
public class Attend extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lesson_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__attend__lesson__id")
    )
    private Lesson lesson;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__attend__student__id")
    )
    private Student student;

    @Column(name = "visited", nullable = false)
    private boolean visited;

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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(final boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Attend{" +
                "student = " + student + "," + '\n' +
                "visited = " + visited +
                '}';
    }
}
