package by.itacademy.dao;

import by.itacademy.address.Address;
import by.itacademy.assessment.Assessment;
import by.itacademy.attend.Attend;
import by.itacademy.dao.impl.*;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.parent.Parent;
import by.itacademy.schedule.Schedule;
import by.itacademy.school.School;
import by.itacademy.student.Student;
import by.itacademy.studentGroup.StudentGroup;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.PersistenceException;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

public class DaoProviderImpl implements DaoProvider {

    private final Map<Class<?>, GenericDao<?>> daoMap;

    private final SessionFactory sessionFactory;

    public DaoProviderImpl(final SessionFactory sessionFactory) {
        this.daoMap = new HashMap<>();
        this.sessionFactory = sessionFactory;

        daoMap.put(Address.class, new AddressDao(sessionFactory));
        daoMap.put(Assessment.class, new AssessmentDao(sessionFactory));
        daoMap.put(Attend.class, new AttendDao(sessionFactory));
        daoMap.put(GroupRoom.class, new GroupRoomDao(sessionFactory));
        daoMap.put(Lesson.class, new LessonDao(sessionFactory));
        daoMap.put(Parent.class, new ParentDao(sessionFactory));
        daoMap.put(Schedule.class, new ScheduleDao(sessionFactory));
        daoMap.put(School.class, new SchoolDao(sessionFactory));
        daoMap.put(Student.class, new StudentDao(sessionFactory));
        daoMap.put(StudentGroup.class, new StudentGroupDao(sessionFactory));
        daoMap.put(Subject.class, new SubjectDao(sessionFactory));
        daoMap.put(Teacher.class, new TeacherDao(sessionFactory));
    }

    @Override
    public void close() throws PersistenceException {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> GenericDao<T> provide(final Class<T> entityClass) {
        GenericDao<T> dao = (GenericDao<T>) daoMap.get(entityClass);
        if (dao != null) {
            return dao;
        }
        throw new IllegalArgumentException("Dao for entity class " + entityClass + " does not exist");
    }
}
