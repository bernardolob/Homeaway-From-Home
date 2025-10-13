package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Iterator Abstract Data Type with Filter
 * Includes description of general methods for one way iterator.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class FilterIterator<E> implements Iterator<E> {

    /**
     * Iterator of elements to filter.
     */
    private final Iterator<E> iterator;

    /**
     *  Filter.
     */
    private final Predicate<E> filter;

    /**
     * Node with the next element in the iteration.
     */
    private E nextToReturn;

    /**
     * Boolean that says if the nextToReturn prepared.
     */
    private boolean hasNextPrepared;

    /**
     * Constructor.
     *
     * @param list   the iterator to filter
     * @param filter the predicate to apply
     */
    public FilterIterator(Iterator<E> list, Predicate<E> filter) {
        iterator = list;
        this.filter = filter;
        this.nextToReturn = null;
        this.hasNextPrepared = false;
    }

    /**
     * Returns true if next would return an element
     *
     * @return true iff the iteration has more elements
     */
    public boolean hasNext() {
        if (!hasNextPrepared)
            advanceNext();
        return hasNextPrepared;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();
        hasNextPrepared = false; // consume this element
        return nextToReturn;
    }

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element.
     */
    public void rewind() {
        iterator.rewind();
        hasNextPrepared = false;
        nextToReturn = null;
    }

    /**
     * Advances the nextToReturn Node in the iterator to a node that checks the filter.
     */
    private void advanceNext() {
        hasNextPrepared = false;
        nextToReturn = null;

        while (iterator.hasNext()) {
            E elem = iterator.next();
            if (filter.check(elem)) {
                nextToReturn = elem;
                hasNextPrepared = true;
                break;
            }
        }
    }
}
