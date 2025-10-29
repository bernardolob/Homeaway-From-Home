package system.service;

import system.student.Student;

import java.io.Serializable;

public interface LimitedService extends Serializable, Service{

    boolean isFull();

    void addStudent(Student student);

    void removeStudent(Student student);
}
