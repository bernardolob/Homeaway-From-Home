package system.student;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;
import system.Country;
import system.service.Lodging;
import system.service.Service;

public abstract class AbstractVisitingStudentClass extends AbstractStudentClass implements VisitingStudent {


    protected List<Service> visits;

    public AbstractVisitingStudentClass(String name, Lodging home, Country country) {
        super(name, home, country);
        visits = new DoublyLinkedList<>();
        processVisit(home);
    }

    public boolean hasNotVisited() {
        return visits.isEmpty();
    }


    public Iterator<Service> getVisitsIterator() {
        return visits.iterator();
    }}
