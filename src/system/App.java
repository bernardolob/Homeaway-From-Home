package system;

import dataStructures.Iterator;
import system.service.Service;
import system.service.ServiceType;
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
}
