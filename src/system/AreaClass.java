package system;

import dataStructures.*;
import system.service.Service;
import system.service.ServiceNameComparator;
import system.student.Student;
import system.student.StudentNameComparator;

public class AreaClass implements Area {

    private static final int NUMBER_OF_RANKS = 5;

    private List<Service> servicesByInsertion;
    private SortedList<Service> servicesByName;
    private List<TwoWayList<Service>> servicesByRank;
    private SortedList<Student> studentsByName;
    private List<Country> countries;

    private final String name;
    private final Coordinates bottomLeft;
    private final Coordinates topRight;

    public AreaClass(String name, Coordinates bottomLeft, Coordinates topRight) {
        this.name = name;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;

        servicesByInsertion = new DoublyLinkedList<>();
        servicesByName = new SortedDoublyLinkedList<>(new ServiceNameComparator());
        servicesByRank = new ListInArray<>(NUMBER_OF_RANKS);
        studentsByName = new SortedDoublyLinkedList<>(new StudentNameComparator());
        countries = new DoublyLinkedList<>();
    }

    public String getAreaName() {
        return name;
    }

    public Coordinates getBottomLeft() {
        return bottomLeft;
    }

    public Coordinates getTopRight() {
        return topRight;
    }
}
