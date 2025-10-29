package system.service;

import system.Coordinates;

public class EatingServiceClass extends AbstractLimitedServiceClass implements Eating {
    public EatingServiceClass(Coordinates coordinates, int price, String name, int capacity) {
        super(coordinates, price, name, capacity);
    }

    public ServiceType getType() {
        return ServiceType.EATING;
    }

    public String getStringType() {
        return ServiceType.EATING.name().toLowerCase();
    }
}
