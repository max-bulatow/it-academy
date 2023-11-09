package by.itacademy.grouproom;

import by.itacademy.BaseEntity;
import by.itacademy.group.SchoolGroup;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "groupRoom")
public class GroupRoom extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schoolGroup_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__groupRoom__schoolGroup__id")
    )
    private SchoolGroup schoolGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__groupRoom__teacher__id")
    )
    private Teacher teacher;

    @OneToMany(mappedBy = "groupRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lesson;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__groupRoom__school__id")
    )
    private School school;

    public SchoolGroup getGroup() {
        return schoolGroup;
    }

    public void setGroup(final SchoolGroup schoolGroup) {
        this.schoolGroup = schoolGroup;
    }

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

    public List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(final List<Lesson> lesson) {
        this.lesson = lesson;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }
}
