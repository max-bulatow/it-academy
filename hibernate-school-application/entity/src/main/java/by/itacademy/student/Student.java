package by.itacademy.student;

import by.itacademy.BasePersonEntity;
import by.itacademy.assessment.Assessment;
import by.itacademy.attend.Attend;
import by.itacademy.parent.Parent;
import by.itacademy.school.School;
import by.itacademy.studentGroup.StudentGroup;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student extends BasePersonEntity {

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<School> schools;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_parent",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_parent__student__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "parent_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_parent__parent__id")
            )
    )
    private List<Parent> parents;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentGroup> studentGroups;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assessment> assessments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attend> attends;

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(final List<Parent> parents) {
        this.parents = parents;
    }

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(final List<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<Attend> getAttends() {
        return attends;
    }

    public void setAttends(final List<Attend> attends) {
        this.attends = attends;
    }

}
