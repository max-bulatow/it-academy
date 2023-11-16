package by.itacademy.teacher;

import by.itacademy.BasePersonEntity;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import by.itacademy.studentGroup.StudentGroup;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends BasePersonEntity {

    @ManyToMany(mappedBy = "teachers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<School> schools;

    @ManyToMany(mappedBy = "teachers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StudentGroup> studentGroups;

    @OneToMany(mappedBy = "roomOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GroupRoom> groupRooms;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lesson> lessons;

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(final List<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
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
        return "Teacher{" +
                "studentGroups = " + studentGroups.stream().toList() + "," + '\n' +
                "groupRooms = " + groupRooms.stream().toList() + "," + '\n' +
                "lessons = " + lessons.stream().toList() + "," + '\n' +
                '}';
    }
}
