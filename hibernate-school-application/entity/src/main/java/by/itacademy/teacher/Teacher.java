package by.itacademy.teacher;

import by.itacademy.assessment.Assessment;
import by.itacademy.BasePersonEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends BasePersonEntity {

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assessment> assessments;

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        if (assessments != null && !assessments.isEmpty()) {
            assessments.forEach(assessment -> assessment.setTeacher(this));
        }
        this.assessments = assessments;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Id = " + super.getId() +
                ", First name = " + super.getFirstName() +
                ", Last name = " + super.getLastName() +
                ", assessment = " + getAssessments() +
                '}';
    }
}
