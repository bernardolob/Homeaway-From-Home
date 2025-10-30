package system.student;

import dataStructures.Iterator;
import system.service.Service;

import java.io.Serializable;

public interface VisitingStudent extends Serializable {

//    /**
//     * Adds a visit to the specified service for the student. The behavior depends on the service type:
//     * - For LEISURE services, the visit is only added if the service has not been visited before, and a flag is set to indicate the student has visited a leisure service.
//     * - For any service, the visit is added if it hasn't been visited before.
//     * - For LODGING and EATING services, the student's current lodging or eating preferences are updated if the new service is cheaper than the current one.
//     * @param s - the service to add to the student's visit list
//     * @pre - the service must be valid and exist in the system
//     */
//    void saveVisit(Service s);


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
