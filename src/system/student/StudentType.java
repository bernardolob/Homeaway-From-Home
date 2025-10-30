package system.student;

import exceptions.InvalidStudentTypeException;
import system.Country;
import system.service.Lodging;

public enum StudentType {
    BOOKISH {
        @Override
        public Student createStudent(String name, Lodging lodging, Country country) {
            return new BookishStudentClass(name, lodging, country);
        }
    },
    OUTGOING {
        @Override
        public Student createStudent(String name, Lodging lodging, Country country) {
            return new OutgoingStudentClass(name, lodging, country);
        }
    },
    THRIFTY {
        @Override
        public Student createStudent(String name, Lodging lodging, Country country) {
            return new ThriftyStudentClass(name, lodging, country);
        }
    };

    public abstract Student createStudent(String name, Lodging lodging, Country country);

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static StudentType fromString(String text) {
        for (StudentType type : values()) {
            if (type.toString().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new InvalidStudentTypeException();
    }
}
