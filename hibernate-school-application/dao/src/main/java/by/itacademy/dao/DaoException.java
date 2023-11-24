package by.itacademy.dao;

public class DaoException extends Exception {

    public DaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static class AddressDaoException extends DaoException {

        public AddressDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class AssessmentDaoException extends DaoException {

        public AssessmentDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class AttendDaoException extends DaoException {

        public AttendDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class GroupRoomDaoException extends DaoException {

        public GroupRoomDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class LessonDaoException extends DaoException {

        public LessonDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class ParentDaoException extends DaoException {

        public ParentDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class ScheduleDaoException extends DaoException {

        public ScheduleDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class SchoolDaoException extends DaoException {

        public SchoolDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class StudentDaoException extends DaoException {

        public StudentDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class StudentGroupDaoException extends DaoException {

        public StudentGroupDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class SubjectDaoException extends DaoException {

        public SubjectDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }

    public static class TeacherDaoException extends DaoException {

        public TeacherDaoException(final String message, final Exception cause) {
            super(message, cause);
        }
    }
}
