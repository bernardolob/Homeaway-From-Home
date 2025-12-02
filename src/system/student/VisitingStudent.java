package system.student;

import dataStructures.Iterator;
import system.service.Service;

import java.io.Serializable;

public interface VisitingStudent extends Serializable {


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
