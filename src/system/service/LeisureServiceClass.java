package system.service;

import system.Coordinates;

public class LeisureServiceClass extends AbstractServiceClass implements Leisure {
    public LeisureServiceClass(Coordinates coordinates, int price, String name, ServiceType type) {
        super(coordinates, price, name, type);
    }
}
