package system.service;

import dataStructures.Comparator;

/**
* Compares two tags alphabetically, ignoring case.
*/
public class TagComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}
