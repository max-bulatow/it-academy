package by.itacademy.teacher;

import by.itacademy.BasePersonEntity;
import by.itacademy.assessment.Assessment;
import by.itacademy.group.SchoolGroup;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import by.itacademy.subject.Subject;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends BasePersonEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_schoolGroup",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_schoolGroup__teacher__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "schoolGroup_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_schoolGroup__schoolGroup__id")
            )
    )
    private List<SchoolGroup> schoolGroup;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_subject__teacher__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subjects_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_subject__subject__id")
            )
    )
    private List<Subject> subject;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupRoom> groupRooms;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assessment> assessments;

    @ManyToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<School> schools;

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        if (assessments != null && !assessments.isEmpty()) {
            assessments.forEach(assessment -> assessment.setTeacher(this));
        }
        this.assessments = assessments;
    }

    public List<SchoolGroup> getGroups() {
        return schoolGroup;
    }

    public void setGroups(final List<SchoolGroup> schoolGroups) {
        this.schoolGroup = schoolGroups;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(final List<Subject> subject) {
        this.subject = subject;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<GroupRoom> getGroupRooms() {
        return groupRooms;
    }

    public void setGroupRooms(final List<GroupRoom> groupRooms) {
        this.groupRooms = groupRooms;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Id = " + super.getId() +
                ", First name = " + super.getFirstName() +
                ", Last name = " + super.getLastName() +
                ", assessment = " + getAssessments() +
                '}';
    }
}
