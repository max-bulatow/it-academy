package by.itacademy;

import by.itacademy.group.SchoolGroup;
import by.itacademy.school.School;
import by.itacademy.student.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "school_student")
public class SchoolStudent extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__school_student__school__id")
    )
    private School school;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__school_student__student__id")
    )
    private Student student;

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(final Student student) {
        this.student = student;
    }
}
