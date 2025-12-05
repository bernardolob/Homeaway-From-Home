package system.student;

import system.Coordinates;
import system.service.*;

import java.io.Serializable;

public interface Student extends Serializable {

    /**
     * Retrieves the type of the student.
     * @pre - the student must be properly initialized with a valid type
     * @return - the type of the student (StudentType)
     */
    StudentType getType();


    /**
     * Retrieves the name of the student.
     * @pre - the student must be properly initialized with a valid name
     * @return - the name of the student
     */
    String getName();

    /**
     * Retrives the student's coordinates.
     * @return Student Coordinates
     */
    Coordinates getCoordinates();

    /**
     * Retrieves the lodging service where the student is currently staying.
     * @pre - the student must have a valid lodging assigned
     * @return - the lodging service (LodgingClass) where the student is currently staying
     */
    Lodging getHome();


    /**
     * Sets a new lodging service for the student and updates their location to the new lodging.
     * @param newHome - the new lodging service to assign to the student
     * @pre - the newHome must be a valid LodgingClass object
     */
    void changeHome(Lodging newHome);


    /**
     * Retrieves the current location of the student, which is the service they are currently located at.
     * @pre - the student must have a valid location assigned
     * @return - the service (Service) where the student is currently located
     */
    Service getLocation();


    /**
     * Updates the student's current location to the specified service.
     *
     * @param s - the new service to set as the student's current location
     * @return
     * @pre - the service must be valid
     */
    boolean changeLocation(Service s);

    /**
     * Returns the type of the student (e.g., bookish, outgoing, thrifty).
     *
     * @return - The service type
     */
    String getStringType();


    /**
     * Retrieves the name of current location of the student,
     * which is the service they are currently located at.
     * @pre - the student must have a valid location assigned
     * @return - the service (String) where the student is currently located
     */
    String getLocationName();

    void processVisit(Service service);

    void removeStudent();

}
