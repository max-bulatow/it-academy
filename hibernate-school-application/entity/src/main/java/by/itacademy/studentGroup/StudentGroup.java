package by.itacademy.studentGroup;

import by.itacademy.BaseEntity;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_group")
public class StudentGroup extends BaseEntity {

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student_group__school__id")
    )
    private School school;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "group_owner_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student_group__teacher__id")
    )
    private Teacher groupOwner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_group_student",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"student_group_id", "student_id"})},
            joinColumns = @JoinColumn(
                    name = "student_group_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_group_student__student_group__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_group_student__student__id")
            )
    )
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_group_subject",
            joinColumns = @JoinColumn(
                    name = "student_group_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_group_subject__student_group__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_group_subject__subject__id")
            )
    )
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_group_teacher",
            joinColumns = @JoinColumn(
                    name = "student_group_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_group_teacher__student_group__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_group_teacher__teacher__id")
            )
    )
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupRoom> groupRooms;

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public Teacher getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(final Teacher groupOwner) {
        this.groupOwner = groupOwner;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(final List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<GroupRoom> getGroupRooms() {
        return groupRooms;
    }

    public void setGroupRooms(final List<GroupRoom> groupRooms) {
        this.groupRooms = groupRooms;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                " name = '" + name +
                '}';
    }
}
