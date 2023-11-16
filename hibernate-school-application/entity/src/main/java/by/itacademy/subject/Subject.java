package by.itacademy.subject;

import by.itacademy.BaseEntity;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import by.itacademy.studentGroup.StudentGroup;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject", uniqueConstraints = {@UniqueConstraint(name = "uq__subject__name", columnNames = "name")})
public class Subject extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<School> schools;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentGroup> studentGroups;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name = '" + name + '\'' +
                '}';
    }
}
