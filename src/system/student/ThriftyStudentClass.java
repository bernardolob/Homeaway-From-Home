package system.student;

import exceptions.AlreadyHomeException;
import exceptions.ServiceFullException;
import exceptions.UnacceptableMoveException;
import system.Country;
import system.service.*;

public class ThriftyStudentClass extends AbstractStudentClass implements Thrifty {

    /**
     * CheapestEating the student has ever visited.
     */
    private float cheapestEating;


    public ThriftyStudentClass(String name, Lodging home, Country country) {
        super(name, home, country);
        cheapestEating = -1;
    }


    @Override
    public void changeHome(Lodging newHome) {
        if (newHome == home)
            throw new AlreadyHomeException();
        if (newHome.isFull())
            throw new ServiceFullException();
        if (!newHome.isCheaper(getHome()))
            throw new UnacceptableMoveException();
        super.changeHome(newHome);
    }

    @Override
    public StudentType getType() {
        return StudentType.THRIFTY;
    }

    @Override
    public String getStringType() {
        return StudentType.THRIFTY.toString().toLowerCase();
    }


    /**
     * Tells if a Thrifty Student is distracted.
     * @param s service about to be
     * @return true if is distracted, false otherwise
     */
    public boolean isDistracted(Service s) {
        if (!(s instanceof Eating))
            return false;
        if (cheapestEating == -1)
            return false;
        return s.getPrice() > cheapestEating;
    }

    @Override
    public void processVisit(Service s) {
        if (s instanceof Eating)
            if (cheapestEating == -1 || s.getPrice() < cheapestEating)
                cheapestEating = s.getPrice();
    }

}
