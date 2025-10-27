package system.service;

import Exceptions.ServiceFullException;
import dataStructures.DoublyLinkedList;
import dataStructures.TwoWayList;
import system.Coordinates;

public abstract class AbstractLimitedServiceClass extends AbstractServiceClass implements LimitedService {

    private final TwoWayList<String> presentStudents; // Maybe better to have String instead of Student for Serializable

    private int capacity;

    public AbstractLimitedServiceClass(Coordinates coordinates, int price, String name, ServiceType type, int capacity) {
        super(coordinates, price, name, type);
        this.capacity = capacity;
        presentStudents = new DoublyLinkedList<>();
    }

    public boolean isFull() {
        return capacity == presentStudents.size();
    }

    public void addStudent(String student) {
        if (isFull())
            throw new ServiceFullException();
        presentStudents.addLast(student);
    }

    public void removeStudent(String student) {
        //TODO: Still missing try catch and have to see about the TwoWayList<Student> or String.
        presentStudents.remove(presentStudents.indexOf(student));
    }
}
