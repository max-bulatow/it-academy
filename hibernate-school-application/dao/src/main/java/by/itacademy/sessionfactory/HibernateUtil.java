package by.itacademy.sessionfactory;

import by.itacademy.TeacherSubject;
import by.itacademy.assessment.Assessment;
import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = createSessionFactory();

    public static SessionFactory createSessionFactory() {
        try {
            final Configuration configuration = new Configuration()
                    .addAnnotatedClass(Assessment.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Subject.class)
                    .configure("hibernate.cfg.xml");
            final SessionFactory newSessionFactory = configuration.buildSessionFactory();
            return newSessionFactory;
        } catch (Exception ex) {
            System.err.println("Session Factory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}

