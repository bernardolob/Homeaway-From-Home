package system.service;

import dataStructures.TwoWayIterator;
import system.student.Student;

import java.io.Serializable;

/**
 * Represents a service with a limited capacity (e.g., lodging or other
 * services with a maximum number of participants). Extends the general
 * Service interface.
 */
public interface LimitedService extends Serializable, Service {

    /**
     * Checks whether the service has reached its maximum capacity.
     *
     * @return true if the service is full, false otherwise
     */
    boolean isFull();

    /**
     * Adds a student to the service.
     *
     * @param student student to add
     * @pre student != null
     */
    void addStudent(Student student);

    /**
     * Removes a student from the service.
     *
     * @param student student to remove
     * @pre student != null AND student is present in the service
     */
    void removeStudent(Student student);

    /**
     * Returns a two-way iterator over students currently present in the service.
     *
     * @pre students have been added
     * @return two-way iterator over present students
     */
    TwoWayIterator<Student> getPresentStudents();
}
