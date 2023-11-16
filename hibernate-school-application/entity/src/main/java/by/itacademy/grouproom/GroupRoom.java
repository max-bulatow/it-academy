package by.itacademy.grouproom;

import by.itacademy.BaseEntity;
import by.itacademy.lesson.Lesson;
import by.itacademy.school.School;
import by.itacademy.studentGroup.StudentGroup;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "group_room")
public class GroupRoom extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_group_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__group_room__student_group__id")
    )
    private StudentGroup studentGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "room_owner_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__group_room__teacher__id")
    )
    private Teacher roomOwner;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__group_room__school__id")
    )
    private School school;

    @OneToMany(mappedBy = "groupRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(final StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Teacher getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(final Teacher roomOwner) {
        this.roomOwner = roomOwner;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public List<Lesson> getLesson() {
        return lessons;
    }

    public void setLesson(final List<Lesson> lessons) {
        this.lessons = lessons;
    }


    @Override
    public String toString() {
        return "GroupRoom{" +
                '}';
    }
}
