package system;

import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.TwoWayIterator;
import system.service.Service;
import system.service.ServiceType;
import system.student.Student;
import system.student.StudentType;

/**
 * @author Guilherme Santos (65443) gj.santos@campus.fct.unl.pt
 * @author Bernardo Lobão   (68022) b.lobao@campus.fct.unl.pt
 */
public interface App {

    /**
     * Saves the current area state to a file.
     *
     * @pre the current area must be defined
     * @post the area is saved to persistent storage
     * @return the name of the saved file
     */
    String saveArea();

    /**
     * Loads an area from persistent storage.
     *
     * @param areaName the name of the area file to load
     * @pre areaName must not be null
     * @post the current area is replaced with the loaded one
     */
    void loadArea(String areaName);

    /**
     * Checks whether there is no area currently defined.
     *
     * @return {@code true} if there is no defined area, {@code false} otherwise
     */
    boolean isUndefined();

    /**
     * Creates a new area with the specified coordinates and name.
     *
     * @param xMin the minimum X coordinate
     * @param yMin the minimum Y coordinate
     * @param xMax the maximum X coordinate
     * @param yMax the maximum Y coordinate
     * @param areaName the name of the area to create
     * @pre the coordinates must define a valid rectangular region and not overlap with existing areas
     * @post a new area is created and set as current
     */
    void createArea(long xMin, long yMin, long xMax, long yMax, String areaName);

    /**
     * Adds a new service to the current area.
     *
     * @param type the service type
     * @param latitude the latitude coordinate
     * @param longitude the longitude coordinate
     * @param price the price of the service
     * @param value the value (rating or cost-benefit) of the service
     * @param name the service name
     * @pre the current area must be defined
     * @post the service is added to the area
     */
    void addService(ServiceType type, long latitude, long longitude, int price, int value, String name);

    /**
     * Retrieves an iterator over all services in the current area.
     *
     * @return an iterator of {@code Service} objects
     */
    Iterator<Service> getServicesIterator();

    /**
     * Returns the name of the current area.
     *
     * @return the name of the area
     */
    String getAreaName();

    /**
     * Retrieves a service's name by its identifier.
     *
     * @param serviceName the identifier or name of the service
     * @return the service name if found, {@code null} otherwise
     */
    String getServiceName(String serviceName);

    /**
     * Retrieves a student's name by its identifier.
     *
     * @param studentName the student's identifier or name
     * @return the student name if found, {@code null} otherwise
     */
    String getStudentName(String studentName);

    /**
     * Adds a new student to the current area.
     *
     * @param studentType the type of student
     * @param name the student's name
     * @param country the student's country
     * @param home the student's home address
     * @pre the current area must be defined
     * @post the student is added to the area
     */
    void addStudent(StudentType studentType, String name, String country, String home);

    /**
     * Removes a student by name.
     *
     * @param name the student's name
     * @pre the student must exist in the current area
     * @post the student is removed
     */
    void removeStudent(String name);

    /**
     * Checks if all students from a given country are listed in the area.
     *
     * @param country the country to check
     * @return {@code true} if all students are listed, {@code false} otherwise
     */
    boolean isListingAllStudents(String country);

    /**
     * Returns an iterator over students from a given country.
     *
     * @param country the country whose students are requested
     * @return an iterator of {@code Student} objects
     */
    Iterator<Student> getStudentIterator(String country);

    /**
     * Gets the current location (service) of a given student.
     *
     * @param studentName the student's name
     * @return the {@code Service} the student is currently located at
     */
    Service getStudentLocation(String studentName);

    /**
     * Moves a student to a specified service location.
     *
     * @param name the student's name
     * @param location the target service location name
     * @pre both the student and the service must exist
     * @return {@code true} if the movement was successful, {@code false} otherwise
     */
    boolean goStudent(String name, String location);

    /**
     * Moves a student from their current location to a lodging service.
     *
     * @param student the student's name
     * @param lodging the lodging service name
     * @pre both the student and the lodging must exist
     * @post the student's location is updated
     */
    void move(String student, String lodging);

    /**
     * Returns a bidirectional iterator over students filtered by order and service.
     *
     * @param order the order criterion (e.g., alphabetical, by ranking)
     * @param service the service filter criterion
     * @return a {@code TwoWayIterator} of students
     */
    TwoWayIterator<Student> getUsers(String order, String service);

    /**
     * Returns an iterator over the services visited by a student.
     *
     * @param student the student's name
     * @return an iterator of {@code Service} objects
     */
    Iterator<Service> getVisitsIterator(String student);

    /**
     * Records an evaluation for a service by a student.
     *
     * @param service the service name
     * @param stars the number of stars (rating)
     * @param tags optional tags for the review
     * @pre stars must be within valid range (e.g., 1–5)
     * @post the evaluation is stored
     */
    void evaluate(String service, int stars, List<String> tags);

    /**
     * Returns an iterator of iterators for ranking services.
     *
     * @return an iterator over iterators of ranked services
     */
    Iterator<Iterator<Service>> getRankingIterator();

    /**
     * Returns an iterator over services tagged with the given tag.
     *
     * @param tag the tag to filter services by
     * @return an iterator of {@code Service} objects matching the tag
     */
    Iterator<Service> getTaggedServices(String tag);

    /**
     * Returns an iterator over services ranked by a student with a given rating.
     *
     * @param studentName the student's name
     * @param stars the star rating
     * @param serviceType the type of service to filter
     * @return an iterator of {@code Service} objects
     */
    Iterator<Service> getRankedIterator(String studentName, int stars, String serviceType);

    /**
     * Converts a string representation of a service type to its corresponding ServiceType enum.
     *
     * @param stringType the string representing a service type
     * @pre stringType must not be null
     * @return the corresponding ServiceType enum; {@code null} if no match is found
     */
    ServiceType getServiceType(String stringType);

    /**
     * Converts a string representation of a student type to its corresponding StudentType enum.
     *
     * @param stringType the string representing a student type
     * @pre type must not be null
     * @return the corresponding StudentType enum; {@code null} if no match is found
     */
    StudentType getStudentType(String stringType);

    /**
     * Converts a ServiceType enum to its corresponding string representation.
     *
     * @param type the ServiceType enum to convert
     * @pre type must not be null
     * @return the string representation of the service type; {@code null} if no match is found
     */
    String getServiceType(ServiceType type);

    /**
     * Converts a StudentType enum to its corresponding string representation.
     *
     * @param type the StudentType enum to convert
     * @pre type must not be null
     * @return the string representation of the student type; {@code null} if no match is found
     */
    String getStudentType(StudentType type);

    /**
     * Finds a service of a given type associated with a specific student.
     *
     * @param studentName the student's name
     * @param serviceType the service type name
     * @return the corresponding {@code Service} if found, {@code null} otherwise
     */
    Service find(String studentName, String serviceType);
}
