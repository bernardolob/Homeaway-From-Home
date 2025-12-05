package system;

import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.TwoWayIterator;
import system.service.Service;
import system.service.ServiceType;
import system.student.Student;
import system.student.StudentType;

import java.io.Serializable;

/**
 * Represents a geographical area that contains services and students,
 * allowing queries, updates, and navigation inside its boundaries.
 */
public interface Area extends Serializable {

    /**
     * Gets the name of the area.
     *
     * @return name of the area
     */
    String getAreaName();

    /**
     * Returns the bottom-left coordinate of the area.
     *
     * @return bottom-left coordinates of the area
     */
    Coordinates getBottomLeft();

    /**
     * Returns the top-right coordinate of the area.
     *
     * @return top-right coordinates of the area
     */
    Coordinates getTopRight();

    /**
     * Checks whether a coordinate lies inside the area.
     *
     * @param coordinates coordinates to test
     * @pre coordinates != null
     * @return true if inside the area, false otherwise
     */
    boolean isInside(Coordinates coordinates);

    /**
     * Adds a service to the area.
     *
     * @param type service type
     * @param coordinates service location
     * @param price service price
     * @param value service value or capacity
     * @param name service name
     * @pre coordinates inside area AND name unique
     */
    void addService(ServiceType type, Coordinates coordinates, int price, int value, String name);

    /**
     * Returns an iterator over all services in the area.
     *
     * @return iterator of services
     */
    Iterator<Service> getServicesIterator();

    /**
     * Retrieves a service's name ensuring its existence.
     *
     * @param serviceName name to check
     * @pre service exists
     * @return the same name or a normalized version
     */
    String getServiceName(String serviceName);

    /**
     * Retrieves a student's name ensuring their existence.
     *
     * @param studentName name to check
     * @pre student exists
     * @return the same or normalized student name
     */
    String getStudentName(String studentName);

    /**
     * Adds a student to the area.
     *
     * @param studentType type of student
     * @param name name of the student
     * @param country student's country
     * @param home initial service where the student lives
     * @pre name unique AND home service exists
     */
    void addStudent(StudentType studentType, String name, String country, String home);

    /**
     * Removes a student from the area.
     *
     * @param name student's name
     * @pre student exists
     */
    void removeStudent(String name);

    /**
     * Returns an iterator over all students in the area.
     *
     * @return iterator of students
     */
    Iterator<Student> getAllStudentsIterator();

    /**
     * Returns all students from a given country.
     *
     * @param country country name
     * @return iterator of matching students
     */
    Iterator<Student> getCountryStudentsIterator(String country);

    /**
     * Returns the location (service) where a student currently is.
     *
     * @param studentName student name
     * @pre student exists
     * @return the service where the student is
     */
    Service getStudentLocation(String studentName);

    /**
     * Moves a student to a given service.
     *
     * @param name student name
     * @param location destination service name
     * @pre student and service exist
     * @return true if successful, false if movement invalid
     */
    boolean goStudent(String name, String location);

    /**
     * Sets a student's home service.
     *
     * @param student student name
     * @param lodging service name to set as home
     * @pre student exists AND service exists
     */
    void setHome(String student, String lodging);

    /**
     * Returns a sorted list of users based on some criteria.
     *
     * @param order sorting mode
     * @param service optional service filter
     * @return a two-way iterator over students
     */
    TwoWayIterator<Student> getUsers(String order, String service);

    /**
     * Returns the list of services visited by a student.
     *
     * @param student student's name
     * @pre student exists
     * @return iterator of visited services
     */
    Iterator<Service> getVisitsIterator(String student);

    /**
     * Evaluates a service with stars and tags.
     *
     * @param service service name
     * @param stars rating
     * @param tags list of tags
     * @pre service exists AND 1 <= stars <= 5
     */
    void evaluate(String service, int stars, List<String> tags);

    /**
     * Returns an iterator over service rankings.
     * Each element is itself an iterator of services.
     *
     * @return iterator of iterators (ranked services)
     */
    Iterator<Iterator<Service>> getRankingServices();

    /**
     * Returns all services associated with a given tag.
     *
     * @param tag tag name
     * @return iterator of services
     */
    Iterator<Service> getTaggedServices(String tag);

    /**
     * Returns services filtered by distance, rating, and type.
     *
     * @param studentCoordinates reference coordinates
     * @param stars minimum stars
     * @param serviceType type of service
     * @pre stars in valid range
     * @return iterator of filtered services
     */
    Iterator<Service> getRankedIterator(Coordinates studentCoordinates, int stars, ServiceType serviceType);

    /**
     * Gets the coordinates of a given student.
     *
     * @param studentName student name
     * @pre student exists
     * @return student's coordinates
     */
    Coordinates getStudentCoordinates(String studentName);

    /**
     * Checks if there is at least one service of a certain type.
     *
     * @param type service type
     * @return true if exists, false otherwise
     */
    boolean hasServiceType(ServiceType type);

    /**
     * Checks if the area contains any service whose average rating meets the threshold.
     *
     * @param stars minimum average stars
     * @return true if a service matches, false otherwise
     */
    boolean hasServiceAvg(int stars);

    /**
     * Finds a service based on student name and service type.
     *
     * @param studentName student to reference
     * @param type service type
     * @return the matching service or null if none
     */
    Service find(String studentName, ServiceType type);
}
