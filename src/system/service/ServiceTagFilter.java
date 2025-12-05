package system.service;

import dataStructures.Predicate;

/**
 * Predicate that checks whether a service has a specific tag.
 */
public record ServiceTagFilter(String tag) implements Predicate<Service> {

    @Override
    public boolean check(Service elem) {
        return elem.hasTag(tag);
    }

}
