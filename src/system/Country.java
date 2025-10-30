package system;

import dataStructures.Iterator;
import system.student.Student;

import java.io.Serializable;

public interface Country extends Serializable {

    String getCountryName();

    void addCitizen(Student citizen);

    Iterator<Student> getStudentsIterator();

    void removeCitizen(Student student);
}
