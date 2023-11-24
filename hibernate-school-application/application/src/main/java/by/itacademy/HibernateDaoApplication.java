package by.itacademy;

import by.itacademy.address.Address;
import by.itacademy.assessment.Assessment;
import by.itacademy.attend.Attend;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.DaoProvider;
import by.itacademy.dao.DaoProviderImpl;
import by.itacademy.dao.GenericDao;
import by.itacademy.grouproom.GroupRoom;
import by.itacademy.lesson.Lesson;
import by.itacademy.parent.Parent;
import by.itacademy.schedule.Schedule;
import by.itacademy.school.School;
import by.itacademy.sessionfactory.HibernateUtil;
import by.itacademy.student.Student;
import by.itacademy.studentGroup.StudentGroup;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;
import jakarta.persistence.PersistenceException;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class HibernateDaoApplication {
    public static void main(final String[] args) {
        try (DaoProvider daoProvider = new DaoProviderImpl(HibernateUtil.getSessionFactory())) {

//  Создание объекта address и запись в таблицу
            final Address address = new Address();
            address.setCity("Brest");
            address.setStreet("Dzerjinskogo");
            address.setBuildingNumber("50");

            final GenericDao<Address> addressDao = daoProvider.provide(Address.class);

            addressDao.create(address);

//  Создание нового объекта школа и запись в таблицу
            final School school = new School();
            school.setName("IT-Academy");
            school.setAddress(addressDao.read(1));

            final GenericDao<School> schoolDao = daoProvider.provide(School.class);

            schoolDao.create(school);

//  Создание объекта subject и запись в таблицу
            final Subject subject = new Subject();
            subject.setName("Java core");

            final GenericDao<Subject> subjectDao = daoProvider.provide(Subject.class);

            subjectDao.create(subject);

//  Связывание школы и предметов
            final School schoolWithSubject = schoolDao.read(1);
            schoolWithSubject.setSubjects(List.of(subjectDao.read(1)));
            schoolDao.update(schoolWithSubject);

            final Subject subjectWithSchool = subjectDao.read(1);
            subjectWithSchool.setSchools(List.of(schoolDao.read(1)));
            subjectDao.update(subjectWithSchool);

//  Создание объекта учитель и запись в таблицу
            final Teacher teacher = new Teacher();
            teacher.setFirstName("Petr");
            teacher.setLastName("Petrov");

            final GenericDao<Teacher> teacherDao = daoProvider.provide(Teacher.class);
            teacherDao.create(teacher);

//  Связывание учителя и школы
            final Teacher teacherWithSchool = teacherDao.read(1);
            teacherWithSchool.setSchools(List.of(schoolDao.read(1)));
            teacherDao.update(teacherWithSchool);

            final School schoolWithTeacher = schoolDao.read(1);
            schoolWithTeacher.setTeachers(List.of(teacherDao.read(1)));
            schoolDao.update(schoolWithTeacher);

//  Создание объекта родитель и запись в таблицу
            final Parent parentFather = new Parent();
            parentFather.setFirstName("Andrey");
            parentFather.setLastName("Andreev");

            final Parent parentMother = new Parent();
            parentMother.setFirstName("Inna");
            parentMother.setLastName("Andreeva");

            final GenericDao<Parent> parentDao = daoProvider.provide(Parent.class);
            parentDao.create(parentFather);
            parentDao.create(parentMother);

//  Создание объекта студент и запись в таблицу
            final Student student = new Student();
            student.setFirstName("Stepan");
            student.setLastName("Andreev");
            student.setParents(List.of(parentDao.read(1), parentDao.read(2)));

            final GenericDao<Student> studentDao = daoProvider.provide(Student.class);
            studentDao.create(student);

//  Cвязывание студента и школы
            final Student studentWithSchool = studentDao.read(1);
            studentWithSchool.setSchools(List.of(schoolDao.read(1)));
            studentDao.update(studentWithSchool);

            final School schoolWithStudent = schoolDao.read(1);
            schoolWithStudent.setStudents(List.of(studentDao.read(1)));
            schoolDao.update(schoolWithStudent);

//  Создание объекта класс и запись в таблицу
            final StudentGroup studentGroup = new StudentGroup();
            studentGroup.setName("JC-2023");
            studentGroup.setGroupOwner(teacherDao.read(1));
            studentGroup.setSchool(schoolDao.read(1));

            final GenericDao<StudentGroup> studentGroupDao = daoProvider.provide(StudentGroup.class);
            studentGroupDao.create(studentGroup);

//  Связывание класса и студента
            final StudentGroup studentGroupWithStudent = studentGroupDao.read(1);
            studentGroupWithStudent.setStudents(List.of(studentDao.read(1)));
            studentGroupDao.update(studentGroupWithStudent);

            final Student studentWithStudentGroup = studentDao.read(1);
            studentWithStudentGroup.setStudentGroups(List.of(studentGroupDao.read(1)));
            studentDao.update(studentWithStudentGroup);

//  Связывание класса и предмета
            final StudentGroup studentGroupWithSubject = studentGroupDao.read(1);
            studentGroupWithSubject.setSubjects(List.of(subjectDao.read(1)));
            studentGroupDao.update(studentGroupWithSubject);

            final Subject subjectWithStudentGroup = subjectDao.read(1);
            subjectWithStudentGroup.setStudentGroups(List.of(studentGroupDao.read(1)));
            subjectDao.update(subjectWithStudentGroup);

//  Связывание предмета и учителя
            final StudentGroup studentGroupWithTeacher = studentGroupDao.read(1);
            studentGroupWithTeacher.setTeachers(List.of(teacherDao.read(1)));
            studentGroupDao.update(studentGroupWithTeacher);

            final Teacher teacherWithStudentGroup = teacherDao.read(1);
            teacherWithStudentGroup.setStudentGroups(List.of(studentGroupDao.read(1)));
            teacherDao.update(teacherWithStudentGroup);

//  Создание объекта расписание и запись в таблицу
            final Schedule schedule = new Schedule();
            schedule.setStartDate(OffsetDateTime.of(2023, 12, 10, 8, 0, 0, 0, ZoneOffset.ofHours(3)));
            schedule.setEndDate(OffsetDateTime.of(2023, 12, 10, 19, 0, 0, 0, ZoneOffset.ofHours(3)));
            schedule.setSchool(schoolDao.read(1));

            final GenericDao<Schedule> scheduleDao = daoProvider.provide(Schedule.class);
            scheduleDao.create(schedule);

//  Создание объекта кабинет и запись в таблицу
            final GroupRoom groupRoom = new GroupRoom();
            groupRoom.setStudentGroup(studentGroupDao.read(1));
            groupRoom.setSchool(schoolDao.read(1));
            groupRoom.setRoomOwner(teacherDao.read(1));

            final GenericDao<GroupRoom> groupRoomDao = daoProvider.provide(GroupRoom.class);
            groupRoomDao.create(groupRoom);

//  Создание объекта урок и запись в таблицу
            final Lesson lesson = new Lesson();
            lesson.setStudentGroup(studentGroupDao.read(1));
            lesson.setSubject(subjectDao.read(1));
            lesson.setSchedule(scheduleDao.read(1));
            lesson.setTeacher(teacherDao.read(1));
            lesson.setGroupRoom(groupRoomDao.read(1));

            final GenericDao<Lesson> lessonDao = daoProvider.provide(Lesson.class);
            lessonDao.create(lesson);

//  Создание объекта оценка и запись в таблицу
            final Assessment assessment = new Assessment();
            assessment.setLesson(lessonDao.read(1));
            assessment.setStudent(studentDao.read(1));
            assessment.setAssessment(9);

            final GenericDao<Assessment> assessmentDao = daoProvider.provide(Assessment.class);
            assessmentDao.create(assessment);

//  Создание объекта посещение и запись в таблицу
            final Attend attend = new Attend();
            attend.setLesson(lessonDao.read(1));
            attend.setStudent(studentDao.read(1));
            attend.setVisited(true);

            final GenericDao<Attend> attendDao = daoProvider.provide(Attend.class);
            attendDao.create(attend);

            System.out.println(schoolDao.read(1).toString());
        } catch (DaoException | PersistenceException ex) {
            ex.printStackTrace();
        }
    }
}

