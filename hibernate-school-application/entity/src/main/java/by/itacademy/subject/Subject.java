package by.itacademy.subject;

import by.itacademy.BaseEntity;
import by.itacademy.assessment.Assessment;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject", uniqueConstraints = {@UniqueConstraint(name = "uq__subject__name", columnNames = "name")})
public class Subject extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assessment> assessments;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        if (assessments != null && !assessments.isEmpty()) {
            assessments.forEach(assessment -> assessment.setSubject(this));
        }
        this.assessments = assessments;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "Id = " + super.getId() +
                ", name = " + name +
                ", assessments = " + assessments +
                '}';
    }
}
