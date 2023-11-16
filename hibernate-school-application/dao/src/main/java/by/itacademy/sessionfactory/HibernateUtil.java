package by.itacademy.sessionfactory;

import by.itacademy.address.Address;
import by.itacademy.assessment.Assessment;
import by.itacademy.attend.Attend;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.parent.Parent;
import by.itacademy.schedule.Schedule;
import by.itacademy.school.School;
import by.itacademy.student.Student;
import by.itacademy.studentGroup.StudentGroup;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(School.class)
                    .addAnnotatedClass(Subject.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(Parent.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(StudentGroup.class)
                    .addAnnotatedClass(Schedule.class)
                    .addAnnotatedClass(GroupRoom.class)
                    .addAnnotatedClass(Lesson.class)
                    .addAnnotatedClass(Assessment.class)
                    .addAnnotatedClass(Attend.class);

            configuration.configure("hibernate.cfg.xml");

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




