package system.service;

import system.Coordinates;

public class LodgingServiceClass extends AbstractLimitedServiceClass implements Lodging {


    public LodgingServiceClass(Coordinates coordinates, int price, String name, int capacity) {
        super(coordinates, price, name, capacity);
    }

    public boolean isCheaper(Lodging home) {
        return super.getPrice() < home.getPrice();
    }

    public ServiceType getType() {
        return ServiceType.LODGING;
    }

    public String getStringType() {
        return ServiceType.LODGING.name().toLowerCase();
    }
}
