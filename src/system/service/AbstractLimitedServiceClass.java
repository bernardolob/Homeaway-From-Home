package system.service;

import exceptions.ServiceFullException;
import dataStructures.DoublyLinkedList;
import dataStructures.TwoWayList;
import system.Coordinates;

public abstract class AbstractLimitedServiceClass extends AbstractServiceClass implements LimitedService {

    private final TwoWayList<String> presentStudents; // Maybe better to have String instead of Student for Serializable

    private final int capacity;

    public AbstractLimitedServiceClass(Coordinates coordinates, int price, String name, int capacity) {
        super(coordinates, price, name);
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
