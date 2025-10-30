package system.service;

import dataStructures.Predicate;

public record ServiceTagFilter(String tag) implements Predicate<Service> {

    @Override
    public boolean check(Service elem) {
        return elem.hasTag(tag);
    }

}
