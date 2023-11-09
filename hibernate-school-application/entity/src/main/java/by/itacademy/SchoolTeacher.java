package by.itacademy;

import by.itacademy.school.School;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

@Entity
@Table(name = "school_teacher")
public class SchoolTeacher extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__school_teacher__school__id")
    )
    private School school;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__school_teacher__teacher__id")
    )
    private Teacher teacher;

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }
}
