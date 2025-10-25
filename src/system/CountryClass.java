package system;

import system.student.Student;
import dataStructures.*;
import system.student.StudentNameComparator;

public class CountryClass implements Country {
    private String countryName;
    private SortedList<Student> citizens;

    public CountryClass(String countryName) {
        citizens = new SortedDoublyLinkedList<>(new StudentNameComparator());
    }
}
