package system.student;

import system.Country;
import system.service.Lodging;
import system.service.Service;

public class OutgoingStudentClass extends AbstractVisitingStudentClass implements Outgoing {

    public OutgoingStudentClass(String name, Lodging home, Country country) {
        super(name, home, country);
    }

    public StudentType getType() {
        return StudentType.OUTGOING;
    }

    @Override
    public void processVisit(Service s) {
        if (visits.indexOf(s) == -1) {
            visits.addLast(s);
        }
    }

    @Override
    public String getStringType() {
        return StudentType.OUTGOING.toString().toLowerCase();
    }

}
