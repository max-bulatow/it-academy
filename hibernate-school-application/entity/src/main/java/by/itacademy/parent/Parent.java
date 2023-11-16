package by.itacademy.parent;

import by.itacademy.BasePersonEntity;
import by.itacademy.student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parent")
public class Parent extends BasePersonEntity {

    @ManyToMany(mappedBy = "parents", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }
}
