package by.itacademy.schedule;

import by.itacademy.BaseEntity;
import by.itacademy.group.SchoolGroup;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Column(name = "startDate", nullable = false)
    private OffsetDateTime startDate;

    @Column(name = "endDate", nullable = false)
    private OffsetDateTime endDate;

    @OneToOne(mappedBy = "schedule")
    private SchoolGroup schoolGroup;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__schedule__school__id")
    )
    private School school;

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

    public SchoolGroup getGroup() {
        return schoolGroup;
    }

    public void setGroup(final SchoolGroup schoolGroup) {
        this.schoolGroup = schoolGroup;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }
}
