package system;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;
import system.student.Student;

public class CountryClass implements Country {
    private final String countryName;
    private final List<Student> citizens;

    public CountryClass(String countryName) {
        this.countryName = countryName;
        citizens = new DoublyLinkedList<>();
    }

    @Override
    public String getCountryName() {
        return countryName;
    }

    public void addCitizen(Student citizen) {
        citizens.addLast(citizen);
    }

    @Override
    public Iterator<Student> getStudentsIterator() {
        return citizens.iterator();
    }

    @Override
    public void removeCitizen(Student student) {
        citizens.remove(citizens.indexOf(student));
    }
}
