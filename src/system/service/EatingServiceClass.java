package system.service;

import system.Coordinates;

public class EatingServiceClass extends AbstractLimitedServiceClass implements Eating {
    public EatingServiceClass(Coordinates coordinates, int price, String name, ServiceType type, int capacity) {
        super(coordinates, price, name, type, capacity);
    }
}
