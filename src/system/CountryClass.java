package system;

import system.student.Student;
import dataStructures.*;
import system.student.StudentNameComparator;

public class CountryClass implements Country {
    private String countryName;
    private List<Student> citizens;

    public CountryClass(String countryName) {
        this.countryName = countryName;
        citizens = new DoublyLinkedList<>();
    }
}
