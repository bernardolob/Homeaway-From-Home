package system.student;

import dataStructures.Iterator;
import system.service.Lodging;
import system.service.LodgingServiceClass;
import system.service.Service;

public abstract class AbstractStudentClass implements Student {

    protected String name;
    protected Lodging home;
    protected Service location;

    public AbstractStudentClass(String name, Lodging home) {
        this.name = name;
        this.home = home;
        location  = home;
    }

    public String getName() {
        return name;
    }

    public Lodging getHome() {
        return home;
    }

    public void changeHome(Lodging newHome) {
        home = newHome;
        changeLocation(home);
    }

    public Service getLocation() {
        return location;
    }

    public void changeLocation(Service s) {
        location = s;
    }

    public boolean isThrifty() {
        return false;
    }

    public abstract StudentType getType();

}
