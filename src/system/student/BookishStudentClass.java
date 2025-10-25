package system.student;

import dataStructures.*;
import system.service.*;


public class BookishStudentClass extends AbstractVisitingStudentClass implements Bookish {

    public BookishStudentClass(String name, Lodging home) {
        super(name, home);
    }

    @Override
    public void saveVisit(Service s) {
        if (s.getType().equals(ServiceType.LEISURE) && visits.indexOf(s) == -1) {
            visits.addLast(s);
        }
    }

    @Override
    public StudentType getType() {
        return StudentType.BOOKISH;
    }

}