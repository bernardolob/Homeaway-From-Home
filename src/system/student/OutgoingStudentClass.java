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

    /**
     * If the service isn't recorded yet, the visit is added to the visits list.
     * @param s service
     */
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
