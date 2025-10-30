package system.service;

import dataStructures.TwoWayIterator;
import exceptions.ServiceFullException;
import dataStructures.DoublyLinkedList;
import dataStructures.TwoWayList;
import system.Coordinates;
import system.student.Student;

public abstract class AbstractLimitedServiceClass extends AbstractServiceClass implements LimitedService {

    private final TwoWayList<Student> presentStudents; // Maybe better to have String instead of Student for Serializable

    private final int capacity;

    public AbstractLimitedServiceClass(Coordinates coordinates, int price, String name, int capacity) {
        super(coordinates, price, name);
        this.capacity = capacity;
        presentStudents = new DoublyLinkedList<>();
    }

    public boolean isFull() {
        return capacity == presentStudents.size();
    }

    public void addStudent(Student student) {
        if (isFull())
            throw new ServiceFullException();
        presentStudents.addLast(student);
    }

    public void removeStudent(Student student) {
        //TODO: Still missing try catch.
        presentStudents.remove(presentStudents.indexOf(student));
    }

    public TwoWayIterator<Student> getPresentStudents() {
        return presentStudents.twoWayiterator();
    }
}
