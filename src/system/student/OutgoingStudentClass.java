package system.student;

import system.service.Lodging;
import system.service.Service;

public class OutgoingStudentClass extends AbstractVisitingStudentClass implements Outgoing {

    public OutgoingStudentClass(String name, Lodging home) {
        super(name, home);
    }

    public StudentType getType() {
        return StudentType.OUTGOING;
    }

    @Override
    public void saveVisit(Service s) {
        if (visits.indexOf(s) == -1) {
            visits.addLast(s);
        }
    }

    @Override
    public String getStringType() {
        return StudentType.OUTGOING.toString().toLowerCase();
    }

}
