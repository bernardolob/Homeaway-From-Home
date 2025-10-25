package system.student;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;
import system.service.Lodging;
import system.service.Service;

public abstract class AbstractVisitingStudentClass extends AbstractStudentClass implements VisitingStudent {


    protected List<Service> visits;

    public AbstractVisitingStudentClass(String name, Lodging home) {
        super(name, home);
        visits = new DoublyLinkedList<>();

    }

    @Override
    public void changeHome(Lodging newHome) {
        super.changeHome(newHome);
    }

    @Override
    public boolean hasNotVisited() {
        return visits.isEmpty();
    }

    public abstract void saveVisit(Service s);

    public Iterator<Service> getVisitsIterator() {
        return visits.iterator();
    }}
