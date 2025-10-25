package system.student;

import dataStructures.Iterator;
import system.service.*;

public interface Student {

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
     * @param s - the new service to set as the student's current location
     * @pre - the service must be valid
     */
    void changeLocation(Service s);


    /**
     * Checks if the student is of type Thrifty.
     * @pre - the student's type must be properly initialized
     * @return - true if the student is Thrifty, false otherwise
     */
    boolean isThrifty();


    /**
     * Checks if the student has not visited any service.
     * @pre - the student's visit status must be properly initialized
     * @return - true if the student has not visited any service, false otherwise
     */
    boolean hasNotVisited();


    /**
     * Retrieves an iterator for the list of services the student has visited.
     * @pre - the visits list must be initialized and contain the services the student has visited
     * @return - an iterator for the student's visited services
     */
    Iterator<Service> getVisitsIterator();

}
