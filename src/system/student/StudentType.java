package system.student;

import system.service.Lodging;

public enum StudentType {
    BOOKISH {
        @Override
        public Student createStudent(String name, Lodging lodging) {
            return new BookishStudentClass(name, lodging);
        }
    },
    OUTGOING {
        @Override
        public Student createStudent(String name, Lodging lodging) {
            return new OutgoingStudentClass(name, lodging);
        }
    },
    THRIFTY {
        @Override
        public Student createStudent(String name, Lodging lodging) {
            return new ThriftyStudentClass(name, lodging);
        }
    };

    public abstract Student createStudent(String name, Lodging lodging);
}
