package by.itacademy;

import by.itacademy.assessment.Assessment;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.impl.*;
import by.itacademy.student.Student;
import by.itacademy.subject.Subject;
import by.itacademy.teacher.Teacher;

import java.time.OffsetDateTime;

public class HibernateDaoApplication {
    public static void main(final String[] args) {
        final StudentDao studentDao = new StudentDao();

        try {
            final Student studentCreate = new Student();
            studentCreate.setFirstName("Aleksey");
            studentCreate.setLastName("Alekseev");
            studentDao.create(studentCreate);
            System.out.println("Created student: " + studentCreate);

            final Student studentRead = studentDao.read(7);
            System.out.println("Read student: " + studentRead.toString());

            final Student studentUpdate = new Student();
            studentUpdate.setId(7);
            studentUpdate.setFirstName("Anna");
            studentUpdate.setLastName("Antonova");
            studentDao.update(studentUpdate);
            final Student studentAfterUpdate = studentDao.read(7);
            System.out.println("Student after update: " + studentAfterUpdate.toString());

            studentDao.delete(10);
            final Student studentDelete = studentDao.read(10);
            if (studentDelete == null) {
                System.out.println("Student delete successful");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        final SubjectDao subjectDao = new SubjectDao();

        try {
            final Subject subjectCreate = new Subject();
            subjectCreate.setName("English literature");
            subjectDao.create(subjectCreate);
            System.out.println("Created subject: " + subjectCreate);

            final Subject subjectRead = subjectDao.read(3);
            System.out.println("Read subject " + subjectRead.toString());

            final Subject subjectUpdate = new Subject();
            subjectUpdate.setId(3);
            subjectUpdate.setName("Russian literature");
            subjectDao.update(subjectUpdate);
            final Subject subjectAfterUpdate = subjectDao.read(3);
            System.out.println("Subject after update: " + subjectAfterUpdate.toString());

            subjectDao.delete(6);
            final Subject subjectDelete = subjectDao.read(6);
            if (subjectDelete == null) {
                System.out.println("Subject delete successful");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        final TeacherDao teacherDao = new TeacherDao();

        try {
            final Teacher teacherCreate = new Teacher();
            teacherCreate.setFirstName("Inna");
            teacherCreate.setLastName("Ilonovna");
            teacherDao.create(teacherCreate);
            System.out.println("Created Teacher: " + teacherCreate);

            final Teacher teacherRead = teacherDao.read(2);
            System.out.println("Read teacher: " + teacherRead.toString());

            final Teacher teacherUpdate = new Teacher();
            teacherUpdate.setId(2);
            teacherUpdate.setFirstName("Marina");
            teacherUpdate.setLastName("Sergeva");
            teacherDao.update(teacherUpdate);
            final Teacher teacherAfterUpdate = teacherDao.read(2);
            System.out.println("Teacher after update: " + teacherAfterUpdate);

            teacherDao.delete(5);
            final Teacher teacherDelete = teacherDao.read(5);
            if (teacherDelete == null) {
                System.out.println("Teacher delete successful");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        final AssessmentDao assessmentDao = new AssessmentDao();

        try {
            final Assessment assessmentCreate = new Assessment();
            assessmentCreate.setAssessment(9);
            assessmentCreate.setCreated(OffsetDateTime.now());
            final Student student = studentDao.read(9);
            final Subject subject = subjectDao.read(12);
            final Teacher teacher = teacherDao.read(5);

            assessmentCreate.setSubject(subject);
            assessmentCreate.setStudent(student);
            assessmentCreate.setTeacher(teacher);

            assessmentDao.create(assessmentCreate);
            System.out.println("Created assessment: " + assessmentCreate);

            final Assessment assessmentRead = assessmentDao.read(7);
            System.out.println("Read assessment: " + assessmentRead);

            final Assessment assessmentUpdate = new Assessment();
            assessmentUpdate.setId(7);
            assessmentUpdate.setTeacher(teacherDao.read(6));
            assessmentUpdate.setAssessment(7);
            assessmentUpdate.setSubject(subjectDao.read(12));
            assessmentUpdate.setStudent(studentDao.read(7));
            assessmentUpdate.setCreated(OffsetDateTime.now());
            assessmentDao.update(assessmentUpdate);
            final Assessment assessmentAfterUpdate = assessmentDao.read(7);
            System.out.println("Assessment after update: " + assessmentAfterUpdate);

            assessmentDao.delete(9);
            final Assessment assessmentDelete = assessmentDao.read(9);
            if (assessmentDelete == null) {
                System.out.println("Assessment delete successful");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        /*final TeacherSubjectDao teacherSubjectDao = new TeacherSubjectDao();

        final TeacherSubject teacherSubjectCreate = new TeacherSubject();
        try {
            teacherSubjectCreate.setSubject(subjectDao.read(3));
            teacherSubjectCreate.setTeacher(teacherDao.read(2));
            teacherSubjectDao.create(teacherSubjectCreate);
            System.out.println("Created teacher_subject: " + teacherSubjectCreate);

            final TeacherSubject teacherSubjectRead = teacherSubjectDao.read(1);
            System.out.println("Read teacher_subject: " + teacherSubjectRead);

            final TeacherSubject teacherSubjectUpdate = new TeacherSubject();
            teacherSubjectUpdate.setId(1);
            teacherSubjectUpdate.setTeacher(teacherDao.read(2));
            teacherSubjectUpdate.setSubject(subjectDao.read(10));
            teacherSubjectDao.update(teacherSubjectUpdate);
            final TeacherSubject teacherSubjectAfterUpdate = teacherSubjectDao.read(1);
            System.out.println("teacher_subject after update: " + teacherSubjectAfterUpdate);

            teacherSubjectDao.delete(1);
            final TeacherSubject teacherSubjectDelete = teacherSubjectDao.read(1);
            if (teacherSubjectDelete == null) {
                System.out.println("teacher_subject delete successful");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }*/
    }
}

