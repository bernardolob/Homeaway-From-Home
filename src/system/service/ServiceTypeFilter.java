package system.service;

import dataStructures.Predicate;

/**
 * Predicate that checks whether a service is a specific ServiceType.
 */
public record ServiceTypeFilter(ServiceType type) implements Predicate<Service> {

    @Override
    public boolean check(Service elem) {
        return elem.getType().equals(type);
    }

}
