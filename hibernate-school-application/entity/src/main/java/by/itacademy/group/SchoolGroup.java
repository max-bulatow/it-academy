package by.itacademy.group;

import by.itacademy.BaseEntity;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.schedule.Schedule;
import by.itacademy.school.School;
import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schoolGroup")
public class SchoolGroup extends BaseEntity {
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @OneToMany(mappedBy = "schoolGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToMany(mappedBy = "schoolGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "schoolGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "schoolGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @OneToMany(mappedBy = "schoolGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupRoom> groupRooms;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schedule_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__schoolGroup__schedule__id")
    )
    private Schedule schedule;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__schoolGroup__school_id")
    )
    private School school;

    public List<GroupRoom> getGroupRooms() {
        return groupRooms;
    }

    public void setGroupRooms(final List<GroupRoom> groupRooms) {
        this.groupRooms = groupRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(final List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(final Schedule schedule) {
        this.schedule = schedule;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }
}
