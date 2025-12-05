package system.student;

import exceptions.InvalidStudentTypeException;
import system.Country;
import system.service.Lodging;

/**
 * Enum representing the types of students in the system.
 * Each type defines how to create a corresponding student instance.
 */
public enum StudentType {

    /**
     * Represents a bookish student.
     */
    BOOKISH {
        /**
         * Creates a new BookishStudentClass instance.
         *
         * @param name student name
         * @param lodging student's lodging
         * @param country student's country
         * @return a BookishStudentClass instance
         */
        @Override
        public Student createStudent(String name, Lodging lodging, Country country) {
            return new BookishStudentClass(name, lodging, country);
        }
    },

    /**
     * Represents an outgoing student.
     */
    OUTGOING {
        /**
         * Creates a new OutgoingStudentClass instance.
         *
         * @param name student name
         * @param lodging student's lodging
         * @param country student's country
         * @return an OutgoingStudentClass instance
         */
        @Override
        public Student createStudent(String name, Lodging lodging, Country country) {
            return new OutgoingStudentClass(name, lodging, country);
        }
    },

    /**
     * Represents a thrifty student.
     */
    THRIFTY {
        /**
         * Creates a new ThriftyStudentClass instance.
         *
         * @param name student name
         * @param lodging student's lodging
         * @param country student's country
         * @return a ThriftyStudentClass instance
         */
        @Override
        public Student createStudent(String name, Lodging lodging, Country country) {
            return new ThriftyStudentClass(name, lodging, country);
        }
    };

    /**
     * Abstract method to create a student instance based on type.
     *
     * @param name student name
     * @param lodging student's lodging
     * @param country student's country
     * @return student instance of the corresponding type
     */
    public abstract Student createStudent(String name, Lodging lodging, Country country);

    /**
     * Returns the lowercase string representation of the student type.
     *
     * @return student type as lowercase string
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * Converts a string to a StudentType enum.
     *
     * @param text string representation of a student type
     * @pre text != null
     * @return matching StudentType enum
     * @throws InvalidStudentTypeException if no matching type is found
     */
    public static StudentType fromString(String text) {
        for (StudentType type : values()) {
            if (type.toString().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new InvalidStudentTypeException();
    }
}
