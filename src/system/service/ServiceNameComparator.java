package system.service;

import dataStructures.Comparator;

public class ServiceNameComparator implements Comparator<Service> {

    @Override
    public int compare(Service s1, Service s2) {
        return s1.getName().compareToIgnoreCase(s2.getName());
    }
}
