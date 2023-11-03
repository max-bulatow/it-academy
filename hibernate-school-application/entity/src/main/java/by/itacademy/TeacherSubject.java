package by.itacademy;

import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_subject")
public class TeacherSubject extends BaseEntity{

    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(final Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher_Subject{" +
                "Id = " + super.getId() +
                ", teacher = {teacher id = " + getTeacher().getId() +
                ", teacher first name = " + getTeacher().getFirstName() +
                ", teacher last name = " + getTeacher().getLastName() + " }" +
                ", subject = {subject id = " + getSubject().getId() +
                ", subject name = " + getSubject().getName() +
                '}';
    }
}
