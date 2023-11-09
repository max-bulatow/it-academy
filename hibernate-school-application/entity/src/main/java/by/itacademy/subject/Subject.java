package by.itacademy.subject;

import by.itacademy.BaseEntity;
import by.itacademy.assessment.Assessment;
import by.itacademy.group.SchoolGroup;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject", uniqueConstraints = {@UniqueConstraint(name = "uq__subject__name", columnNames = "name")})
public class Subject extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "subject")
    private List<Teacher> teachers;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__subject__group_id")
    )
    private SchoolGroup schoolGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lesson_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__subject__lesson_id")
    )
    private Lesson lesson;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__subject__school_id")
    )
    private School school;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assessment> assessments;

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(final List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public SchoolGroup getGroup() {
        return schoolGroup;
    }

    public void setGroup(final SchoolGroup schoolGroup) {
        this.schoolGroup = schoolGroup;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(final Lesson lesson) {
        this.lesson = lesson;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        if (assessments != null && !assessments.isEmpty()) {
            assessments.forEach(assessment -> assessment.setSubject(this));
        }
        this.assessments = assessments;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "Id = " + super.getId() +
                ", name = " + name +
                ", assessments = " + assessments +
                '}';
    }
}
