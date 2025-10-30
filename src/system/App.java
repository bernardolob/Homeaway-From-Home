package system;

import dataStructures.Iterator;
import dataStructures.TwoWayIterator;
import system.service.Service;
import system.service.ServiceType;
import system.student.Student;
import system.student.StudentType;

public interface App {

    String saveArea();

    void loadArea(String areaName);

    boolean isUndefined();

    void createArea(long xMin, long yMin, long xMax, long yMax, String areaName);

    void addService(ServiceType type, long latitude, long longitude, int price, int value, String name);

    Iterator<Service> getServicesIterator();

    String getAreaName();

    String getServiceName(String serviceName);

    String getStudentName(String studentName);

    void addStudent(StudentType studentType, String name, String country, String home);

    void removeStudent(String name);

    boolean isListingAllStudents(String country);

    Iterator<Student> getStudentIterator(String country);

    Service getStudentLocation(String studentName);

    boolean goStudent(String name, String location);

    void move(String student, String lodging);

    TwoWayIterator<Student> getUsers(String order, String service);

    Iterator<Service> getVisitsIterator(String student);

    void evaluate(String service, int stars, String tag);

    Iterator<Iterator<Service>> getRankingIterator();

    Iterator<Service> getTaggedServices(String tag);

    Iterator<Service> getRankedIterator(String studentName, int stars, String serviceType);

    /**
     * Converts a string representation of a service type to its corresponding ServiceType enum.
     *
     * @param stringType - the string representing a service type
     * @pre - stringType must not be null
     * @return - the corresponding ServiceType enum; null if no match is found
     */
    ServiceType getServiceType(String stringType);

    /**
     * Converts a string representation of a student type to its corresponding StudentType enum.
     *
     * @param stringType - the string representing a student type
     * @pre - type must not be null
     * @return - the corresponding StudentType enum; null if no match is found
     */
    StudentType getStudentType(String stringType);

    /**
     * Converts a ServiceType enum to its corresponding string representation.
     *
     * @param type - the ServiceType enum to convert
     * @pre - type must not be null
     * @return - the string representation of the service type; null if no match is found
     */
    String getServiceType(ServiceType type);

    /**
     * Converts a StudentType enum to its corresponding string representation.
     *
     * @param type - the StudentType enum to convert
     * @pre - type must not be null
     * @return - the string representation of the student type; null if no match is found
     */
    String getStudentType(StudentType type);

    Service find(String studentName, String serviceType);
}
