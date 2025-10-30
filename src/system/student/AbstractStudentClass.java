package system.student;

import exceptions.AlreadyHomeException;
import exceptions.AlreadyThereException;
import exceptions.ServiceFullException;
import system.Coordinates;
import system.Country;
import system.service.Eating;
import system.service.LimitedService;
import system.service.Lodging;
import system.service.Service;

public abstract class AbstractStudentClass implements Student {

    protected String name;
    protected Lodging home;
    protected Service location;
    protected Country country;

    public AbstractStudentClass(String name, Lodging home, Country country) {
        this.name = name;
        this.home = home;
        location  = home;
        this.country = country;
        home.addStudent(this);
        country.addCitizen(this);
    }

    public String getName() {
        return name;
    }

    public Lodging getHome() {
        return home;
    }

    public void changeHome(Lodging newHome) {
        if (newHome == home)
            throw new AlreadyHomeException();
        changeLocation(newHome);
        home.removeStudent(this);
        home = newHome;
    }

    public Service getLocation() {
        return location;
    }

    public boolean changeLocation(Service service) {
        if (location == service)
            throw new AlreadyThereException();
        if (service instanceof LimitedService) {
            if (((LimitedService) service).isFull())
                throw new ServiceFullException();
            ((LimitedService) service).addStudent(this);
        }
        if (location instanceof Eating)
            ((Eating) location).removeStudent(this);
        saveVisit(service);
        location = service;
        return isDistracted(service);
    }

    public abstract StudentType getType();

    @Override
    public String getLocationName() {
        return getLocation().getName();
    }

    public boolean isDistracted(Service s) {
        return false;
    }

    public abstract void saveVisit(Service s);

    @Override
    public void removeStudent() {
        home.removeStudent(this);
        if (location != home && location instanceof LimitedService)
            ((LimitedService) location).removeStudent(this);
        country.removeCitizen(this);
    }

    @Override
    public Coordinates getCoordinates() {
        return getLocation().getCoordinates();
    }
}
