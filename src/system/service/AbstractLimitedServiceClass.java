package system.service;

import system.Coordinates;

public abstract class AbstractLimitedServiceClass extends AbstractServiceClass implements LimitedService {

    private int capacity;

    public AbstractLimitedServiceClass(Coordinates coordinates, int price, String name, ServiceType type, int capacity) {
        super(coordinates, price, name, type);
        this.capacity = capacity;
    }
}
