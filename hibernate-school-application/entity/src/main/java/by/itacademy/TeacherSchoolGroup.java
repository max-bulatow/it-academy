package by.itacademy;

import by.itacademy.group.SchoolGroup;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_schoolGroup")
public class TeacherSchoolGroup extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__teacher_schoolGroup__teacher__id")
    )
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schoolGroup_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__teacher_schoolGroup__schoolGroup__id")
    )
    private SchoolGroup schoolGroup;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }

    public SchoolGroup getSchoolGroup() {
        return schoolGroup;
    }

    public void setSchoolGroup(final SchoolGroup schoolGroup) {
        this.schoolGroup = schoolGroup;
    }
}
