package system.student;

import dataStructures.Iterator;
import system.service.Lodging;
import system.service.LodgingServiceClass;
import system.service.Service;

public class ThriftyStudentClass extends AbstractStudentClass implements Thrifty {


    public ThriftyStudentClass(String name, Lodging home) {
        super(name, home);
    }

    @Override
    public void changeHome(Lodging newHome) {
        if (newHome.isCheaper(getHome()))
            super.changeHome(newHome);
    }

    public StudentType getType() {
        return StudentType.THRIFTY;
    }

    public boolean isThrifty() {
        return true;
    }

    @Override
    public boolean hasNotVisited() {
        return false;
    }

    @Override
    public Iterator<Service> getVisitsIterator() {
        return null;
    }

    @Override
    public String getStringType() {
        return StudentType.THRIFTY.toString().toLowerCase();
    }

}
