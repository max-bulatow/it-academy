package by.itacademy.student;

import by.itacademy.assessment.Assessment;
import by.itacademy.BasePersonEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student extends BasePersonEntity {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assessment> assessments;

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        if (assessments != null && !assessments.isEmpty()) {
            assessments.forEach(assessment -> assessment.setStudent(this));
        }
        this.assessments = assessments;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id = " + super.getId() +
                ", First name = " + super.getFirstName() +
                ", Last name = " + super.getLastName() +
                ", assessment = " + getAssessments() +
                '}';
    }
}
