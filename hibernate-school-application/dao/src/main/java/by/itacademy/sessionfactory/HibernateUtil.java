package by.itacademy.sessionfactory;

import by.itacademy.SchoolStudent;
import by.itacademy.SchoolTeacher;
import by.itacademy.TeacherSchoolGroup;
import by.itacademy.TeacherSubject;
import by.itacademy.address.Address;
import by.itacademy.assessment.Assessment;
import by.itacademy.attend.Attend;
import by.itacademy.group.SchoolGroup;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.parent.Parent;
import by.itacademy.schedule.Schedule;
import by.itacademy.school.School;
import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");
            configuration
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Assessment.class)
                    .addAnnotatedClass(Attend.class)
                    .addAnnotatedClass(SchoolGroup.class)
                    .addAnnotatedClass(GroupRoom.class)
                    .addAnnotatedClass(Lesson.class)
                    .addAnnotatedClass(Parent.class)
                    .addAnnotatedClass(Schedule.class)
                    .addAnnotatedClass(School.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Subject.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(SchoolStudent.class)
                    .addAnnotatedClass(SchoolTeacher.class)
                    .addAnnotatedClass(TeacherSchoolGroup.class)
                    .addAnnotatedClass(TeacherSubject.class);

            SessionFactory sessionFactory = configuration.buildSessionFactory();

            return sessionFactory;

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory created failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}




