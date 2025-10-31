package system.service;

import dataStructures.Comparator;

public class RankingComparator implements Comparator<Service> {

    @Override
    public int compare(Service s1, Service s2) {
        return s1.getAverageStars() - s2.getAverageStars();
    }
}
