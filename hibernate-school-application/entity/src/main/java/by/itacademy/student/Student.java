package by.itacademy.student;

import by.itacademy.assessment.Assessment;
import by.itacademy.BasePersonEntity;
import by.itacademy.attend.Attend;
import by.itacademy.group.SchoolGroup;
import by.itacademy.lesson.Lesson;
import by.itacademy.parent.Parent;
import by.itacademy.school.School;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student extends BasePersonEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "parent_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student__parent__id")
    )
    private Parent parent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schoolGroup_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student__schoolGroup__id")
    )
    private SchoolGroup schoolGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lesson_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student__lesson__id")
    )
    private Lesson lesson;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assessment> assessments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attend> attends;

    @ManyToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<School> schools;


    public Parent getParent() {
        return parent;
    }

    public void setParent(final Parent parent) {
        this.parent = parent;
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

    public List<Attend> getAttends() {
        return attends;
    }

    public void setAttends(final List<Attend> attends) {
        this.attends = attends;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        if (assessments != null && !assessments.isEmpty()) {
            assessments.forEach(assessment -> assessment.setStudent(this));
        }
        this.assessments = assessments;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Student{" +
                "parent=" + parent +
                ", schoolGroup=" + schoolGroup +
                ", lesson=" + lesson +
                ", assessments=" + assessments +
                ", attends=" + attends +
                ", schools=" + schools +
                '}';
    }
}
