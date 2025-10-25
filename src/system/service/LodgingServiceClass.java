package system.service;

import system.Coordinates;

public class LodgingServiceClass extends AbstractLimitedServiceClass implements Lodging {


    public LodgingServiceClass(Coordinates coordinates, int price, String name, ServiceType type, int capacity) {
        super(coordinates, price, name, type, capacity);
    }

    public boolean isCheaper(Lodging home) {
        return super.getPrice() < home.getPrice();
    }
}
