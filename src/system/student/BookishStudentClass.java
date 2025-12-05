package system.student;

import dataStructures.*;
import system.Country;
import system.service.*;


public class BookishStudentClass extends AbstractVisitingStudentClass implements Bookish {

    public BookishStudentClass(String name, Lodging home, Country country) {
        super(name, home, country);
    }

    /**
     * Record visit only if the service is a Leisure service
     * @param s service
     */
    @Override
    public void processVisit(Service s) {
        if (s.getType().equals(ServiceType.LEISURE) && visits.indexOf(s) == -1) {
            visits.addLast(s);
        }
    }

    @Override
    public StudentType getType() {
        return StudentType.BOOKISH;
    }

    @Override
    public String getStringType() {
        return StudentType.BOOKISH.toString().toLowerCase();
    }


}