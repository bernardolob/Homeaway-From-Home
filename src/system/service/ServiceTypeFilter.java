package system.service;

import dataStructures.Predicate;

public record ServiceTypeFilter(ServiceType type) implements Predicate<Service> {

    @Override
    public boolean check(Service elem) {
        return elem.getType().equals(type);
    }

}
