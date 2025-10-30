package system.service;

import dataStructures.Predicate;
import system.Coordinates;

public record ServiceTypeFilter(ServiceType type) implements Predicate<Service> {

    @Override
    public boolean check(Service elem) {
        return elem.getType().equals(type);
    }

}
