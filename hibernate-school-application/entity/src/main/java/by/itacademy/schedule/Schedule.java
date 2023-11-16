package by.itacademy.schedule;

import by.itacademy.BaseEntity;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private OffsetDateTime endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__schedule__school__id")
    )
    private School school;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }


    @Override
    public String toString() {
        return "Schedule{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
