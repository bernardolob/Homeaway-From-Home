package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * SepChain Hash Table Iterator
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
class SepChainHashTableIterator<K,V> implements Iterator<Map.Entry<K,V>> {

    private final Map<K,V>[] table;
    private int currentIndex;
    private Iterator<Map.Entry<K,V>> currentIterator;

    public SepChainHashTableIterator(Map<K,V>[] table) {
        this.table = table;
        rewind();
    }

    /**
     * Returns true if next would return an element
     * rather than throwing an exception.
     *
     * @return true iff the iteration has more elements
     */
    public boolean hasNext() {
        if (currentIterator != null && currentIterator.hasNext())
            return true;
        while (currentIndex < table.length) {
            currentIterator = table[currentIndex].iterator();
            currentIndex++;

            if (currentIterator.hasNext())
                return true;
        }

        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    public Map.Entry<K,V> next() {
        if (!hasNext())
            throw new NoSuchElementException();

        return currentIterator.next();
    }

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element.
     */
    public void rewind() {
        currentIndex = 0;
        currentIterator = null;

        while (currentIndex < table.length) {
            currentIterator = table[currentIndex].iterator();
            currentIndex++;

            if (currentIterator.hasNext())
                return;
        }

        currentIterator = null;
    }
}

