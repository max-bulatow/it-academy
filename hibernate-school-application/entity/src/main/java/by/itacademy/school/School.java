package by.itacademy.school;

import by.itacademy.BaseEntity;
import by.itacademy.address.Address;
import by.itacademy.group.SchoolGroup;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.schedule.Schedule;
import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "school")
public class School extends BaseEntity {

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk__school__address__id")
    )
    private Address address;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SchoolGroup> schoolGroups;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "school_teacher",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_teacher__teacher__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_teacher__school__id")
            )
    )
    private List<Teacher> teacher;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupRoom> groupRooms;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "school_student",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_student__student__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_teacher__school__id")
            )
    )
    private List<Student> student;

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

    public List<SchoolGroup> getGroups() {
        return schoolGroups;
    }

    public void setGroups(final List<SchoolGroup> schoolGroups) {
        this.schoolGroups = schoolGroups;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(final List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(final List<Teacher> teacher) {
        this.teacher = teacher;
    }

    public List<GroupRoom> getGroupRooms() {
        return groupRooms;
    }

    public void setGroupRooms(final List<GroupRoom> groupRooms) {
        this.groupRooms = groupRooms;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(final List<Student> student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", schoolGroups=" + schoolGroups +
                ", schedules=" + schedules +
                ", teacher=" + teacher +
                ", groupRooms=" + groupRooms +
                ", subjects=" + subjects +
                ", student=" + student +
                '}';
    }
}
