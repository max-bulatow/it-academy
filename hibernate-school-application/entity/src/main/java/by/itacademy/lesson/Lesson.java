package by.itacademy.lesson;

import by.itacademy.BaseEntity;
import by.itacademy.assessment.Assessment;
import by.itacademy.group.SchoolGroup;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.schedule.Schedule;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__teacher__id")
    )
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schoolGroup_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__schoolGroup__id")
    )
    private SchoolGroup schoolGroup;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "groupRoom_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__groupRoom__id")
    )
    private GroupRoom groupRoom;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schedule_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__schedule__id")
    )
    private Schedule schedule;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assessment> assessments;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }

    public SchoolGroup getGroup() {
        return schoolGroup;
    }

    public void setGroup(final SchoolGroup schoolGroup) {
        this.schoolGroup = schoolGroup;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final List<Subject> subjects) {
        this.subjects = subjects;
    }

    public GroupRoom getGroupRoom() {
        return groupRoom;
    }

    public void setGroupRoom(final GroupRoom groupRoom) {
        this.groupRoom = groupRoom;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(final Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
