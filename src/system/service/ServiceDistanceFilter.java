package system.service;

import dataStructures.Predicate;
import system.Coordinates;

public record ServiceDistanceFilter(Coordinates coordinates, long minDistance) implements Predicate<Service> {

    @Override
    public boolean check(Service elem) {
        return elem.distanceFrom(coordinates) == minDistance;
    }

}
