package by.itacademy;

import by.itacademy.address.Address;
import by.itacademy.assessment.Assessment;
import by.itacademy.attend.Attend;
import by.itacademy.dao.impl.*;
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
import org.hibernate.SessionFactory;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class HibernateDaoApplication {
    public static void main(final String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

//  Создание объекта address и запись в таблицу
        final Address address = new Address();
        address.setCity("Brest");
        address.setStreet("Dzerjinskogo");
        address.setBuildingNumber("50");

        final AddressDao addressDao = new AddressDao(sessionFactory);

        addressDao.create(address);

//  Создание нового объекта школа и запись в таблицу
        final School school = new School();
        school.setName("IT-Academy");
        school.setAddress(addressDao.read(Address.class, 1));

        final SchoolDao schoolDao = new SchoolDao(sessionFactory);

        schoolDao.create(school);

//  Создание объекта subject и запись в таблицу
        final Subject subject = new Subject();
        subject.setName("Java core");

        final SubjectDao subjectDao = new SubjectDao(sessionFactory);

        subjectDao.create(subject);

//  Связывание школы и предметов
        final School schoolWithSubject = schoolDao.read(School.class, 1);
        schoolWithSubject.setSubjects(List.of(subjectDao.read(Subject.class, 1)));
        schoolDao.update(schoolWithSubject);

        final Subject subjectWithSchool = subjectDao.read(Subject.class, 1);
        subjectWithSchool.setSchools(List.of(schoolDao.read(School.class, 1)));
        subjectDao.update(subjectWithSchool);

//  Создание объекта учитель и запись в таблицу
        final Teacher teacher = new Teacher();
        teacher.setFirstName("Petr");
        teacher.setLastName("Petrov");

        final TeacherDao teacherDao = new TeacherDao(sessionFactory);
        teacherDao.create(teacher);

//  Связывание учителя и школы
        final Teacher teacherWithSchool = teacherDao.read(Teacher.class, 1);
        teacherWithSchool.setSchools(List.of(schoolDao.read(School.class, 1)));
        teacherDao.update(teacherWithSchool);

        final School schoolWithTeacher = schoolDao.read(School.class, 1);
        schoolWithTeacher.setTeachers(List.of(teacherDao.read(Teacher.class, 1)));
        schoolDao.update(schoolWithTeacher);

//  Создание объекта родитель и запись в таблицу
        final Parent parentFather = new Parent();
        parentFather.setFirstName("Andrey");
        parentFather.setLastName("Andreev");

        final Parent parentMother = new Parent();
        parentMother.setFirstName("Inna");
        parentMother.setLastName("Andreeva");

        final ParentDao parentDao = new ParentDao(sessionFactory);
        parentDao.create(parentFather);
        parentDao.create(parentMother);

//  Создание объекта студент и запись в таблицу
        final Student student = new Student();
        student.setFirstName("Stepan");
        student.setLastName("Andreev");
        student.setParents(List.of(parentDao.read(Parent.class, 1), parentDao.read(Parent.class, 2)));

        final StudentDao studentDao = new StudentDao(sessionFactory);
        studentDao.create(student);

//  Cвязывание студента и школы
        final Student studentWithSchool = studentDao.read(Student.class, 1);
        studentWithSchool.setSchools(List.of(schoolDao.read(School.class, 1)));
        studentDao.update(studentWithSchool);

        final School schoolWithStudent = schoolDao.read(School.class, 1);
        schoolWithStudent.setStudents(List.of(studentDao.read(Student.class, 1)));
        schoolDao.update(schoolWithStudent);

//  Создание объекта класс и запись в таблицу
        final StudentGroup studentGroup = new StudentGroup();
        studentGroup.setName("JC-2023");
        studentGroup.setGroupOwner(teacherDao.read(Teacher.class, 1));
        studentGroup.setSchool(schoolDao.read(School.class, 1));

        final StudentGroupDao studentGroupDao = new StudentGroupDao(sessionFactory);
        studentGroupDao.create(studentGroup);

//  Связывание класса и студента
        final StudentGroup studentGroupWithStudent = studentGroupDao.read(StudentGroup.class, 1);
        studentGroupWithStudent.setStudents(List.of(studentDao.read(Student.class, 1)));
        studentGroupDao.update(studentGroupWithStudent);

        final Student studentWithStudentGroup = studentDao.read(Student.class, 1);
        studentWithStudentGroup.setStudentGroups(List.of(studentGroupDao.read(StudentGroup.class, 1)));
        studentDao.update(studentWithStudentGroup);

//  Связывание класса и предмета
        final StudentGroup studentGroupWithSubject = studentGroupDao.read(StudentGroup.class, 1);
        studentGroupWithSubject.setSubjects(List.of(subjectDao.read(Subject.class, 1)));
        studentGroupDao.update(studentGroupWithSubject);

        final Subject subjectWithStudentGroup = subjectDao.read(Subject.class, 1);
        subjectWithStudentGroup.setStudentGroups(List.of(studentGroupDao.read(StudentGroup.class, 1)));
        subjectDao.update(subjectWithStudentGroup);

//  Связывание предмета и учителя
        final StudentGroup studentGroupWithTeacher = studentGroupDao.read(StudentGroup.class, 1);
        studentGroupWithTeacher.setTeachers(List.of(teacherDao.read(Teacher.class, 1)));
        studentGroupDao.update(studentGroupWithTeacher);

        final Teacher teacherWithStudentGroup = teacherDao.read(Teacher.class, 1);
        teacherWithStudentGroup.setStudentGroups(List.of(studentGroupDao.read(StudentGroup.class, 1)));
        teacherDao.update(teacherWithStudentGroup);

//  Создание объекта расписание и запись в таблицу
        final Schedule schedule = new Schedule();
        schedule.setStartDate(OffsetDateTime.of(2023, 12, 10, 8, 0, 0, 0, ZoneOffset.ofHours(3)));
        schedule.setEndDate(OffsetDateTime.of(2023, 12, 10, 19, 0, 0, 0, ZoneOffset.ofHours(3)));
        schedule.setSchool(schoolDao.read(School.class, 1));

        final ScheduleDao scheduleDao = new ScheduleDao(sessionFactory);
        scheduleDao.create(schedule);

//  Создание объекта кабинет и запись в таблицу
        final GroupRoom groupRoom = new GroupRoom();
        groupRoom.setStudentGroup(studentGroupDao.read(StudentGroup.class, 1));
        groupRoom.setSchool(schoolDao.read(School.class, 1));
        groupRoom.setRoomOwner(teacherDao.read(Teacher.class, 1));

        final GroupRoomDao groupRoomDao = new GroupRoomDao(sessionFactory);
        groupRoomDao.create(groupRoom);

//  Создание объекта урок и запись в таблицу
        final Lesson lesson = new Lesson();
        lesson.setStudentGroup(studentGroupDao.read(StudentGroup.class, 1));
        lesson.setSubject(subjectDao.read(Subject.class, 1));
        lesson.setSchedule(scheduleDao.read(Schedule.class, 1));
        lesson.setTeacher(teacherDao.read(Teacher.class, 1));
        lesson.setGroupRoom(groupRoomDao.read(GroupRoom.class, 1));

        final LessonDao lessonDao = new LessonDao(sessionFactory);
        lessonDao.create(lesson);

//  Создание объекта оценка и запись в таблицу
        final Assessment assessment = new Assessment();
        assessment.setLesson(lessonDao.read(Lesson.class, 1));
        assessment.setStudent(studentDao.read(Student.class, 1));
        assessment.setAssessment(9);

        final AssessmentDao assessmentDao = new AssessmentDao(sessionFactory);
        assessmentDao.create(assessment);

//  Создание объекта посещение и запись в таблицу
        final Attend attend = new Attend();
        attend.setLesson(lessonDao.read(Lesson.class, 1));
        attend.setStudent(studentDao.read(Student.class, 1));
        attend.setVisited(true);

        final AttendDao attendDao = new AttendDao(sessionFactory);
        attendDao.create(attend);

        System.out.println(schoolDao.read(School.class, 1).toString());
    }
}

