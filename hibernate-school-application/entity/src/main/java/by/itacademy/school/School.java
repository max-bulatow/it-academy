package by.itacademy.school;

import by.itacademy.BaseEntity;
import by.itacademy.address.Address;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.schedule.Schedule;
import by.itacademy.student.Student;
import by.itacademy.studentGroup.StudentGroup;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "school")
public class School extends BaseEntity {

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__school__address__id")
    )
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "school_subject",
            joinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_subject__school__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_subject__subject__id")
            )
    )
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "school_teacher",
            joinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_teacher__school__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_teacher__teacher__id")
            )
    )
    private List<Teacher> teachers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "school_student",
            joinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_student__school__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_student__student__id")
            )
    )
    private List<Student> students;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StudentGroup> studentGroups;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GroupRoom> groupRooms;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(final List<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(final List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<GroupRoom> getGroupRooms() {
        return groupRooms;
    }

    public void setGroupRooms(final List<GroupRoom> groupRooms) {
        this.groupRooms = groupRooms;
    }

    @Override
    public String toString() {
        return "School{" +
                "name = '" + name + '\'' + "," + '\n' +
                "address = " + address + "," + '\n' +
                "subjects = " + subjects.stream().toList() + "," + '\n' +
                "teachers = " + teachers.stream().toList() + "," + '\n' +
                "student = " + students.stream().toList() + "," + '\n' +
                "student group = " + studentGroups.stream().toList() + "," + '\n' +
                "schedule = " + schedules.stream().toList() + "," + '\n' +
                "group room = " + groupRooms.stream().toList() +
                '}';
    }
}
