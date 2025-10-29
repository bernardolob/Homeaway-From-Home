package system;

import dataStructures.Iterator;
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

    Service getStudentLocationName(String studentName);
}
