package system;

import dataStructures.Iterator;
import dataStructures.TwoWayIterator;
import system.service.Service;
import system.service.ServiceType;
import system.student.Student;
import system.student.StudentType;

import java.io.Serializable;

public interface Area extends Serializable {

    String getAreaName();

    Coordinates getBottomLeft();

    Coordinates getTopRight();

    boolean isInside(Coordinates coordinates);

    void addService(ServiceType type, Coordinates coordinates, int price, int value, String name);

    Iterator<Service> getServicesIterator();

    String getServiceName(String serviceName);

    String getStudentName(String studentName);

    void addStudent(StudentType studentType, String name, String country, String home);

    void removeStudent(String name);

    Iterator<Student> getAllStudentsIterator();

    Iterator<Student> getCountryStudentsIterator(String country);

    Service getStudentLocation(String studentName);

    boolean goStudent(String name, String location);

    void setHome(String student, String lodging);

    TwoWayIterator<Student> getUsers(String order, String service);

    Iterator<Service> getVisitsIterator(String student);

    void evaluate(String service, int stars, String tag);

    Iterator<Iterator<Service>> getRankingServices();

    Iterator<Service> getTaggedServices(String tag);

    Iterator<Service> getRankedIterator(Coordinates studentCoordinates, int stars, ServiceType serviceType);

    Coordinates getStudentCoordinates(String studentName);

    boolean hasServiceType(ServiceType type);

    boolean hasServiceAvg(int stars);

    Service find(String studentName, ServiceType type);
}
