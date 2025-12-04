package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * In-order Binary Tree iterator
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */
public class InOrderIterator<E> implements Iterator<E> {

    /**
     * Node with the current element
     */
    private BTNode<E> next;

    /**
     * Root Node
     */
    private final BTNode<E> root;

    /**
     *
     * @param root node
     */
    public InOrderIterator(BTNode<E> root) {
        this.root=root;
        rewind();
    }

    /**
     * Returns true if next would return an element
     * rather than throwing an exception.
     *
     * @return true iff the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return next!=null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    @Override
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();
        E elem=next.getElement();
        advance();
        return elem;
    }

    private void advance() {
        if (next == null)
            return;
        if (next.getRightChild() != null)
            next = ((BTNode<E>) next.getRightChild()).furtherLeftElement();
        else next = (BTNode<E>) getLastRightParent(next);
    }


    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element.
     */
    public void rewind() {
        if (root==null)
            next=null;
        else
            next=root.furtherLeftElement();
    }

    private Node<E> getLastRightParent(BTNode<E> node) {
        if (node == null || node.getParent() == null)
            return null;
        if (node.equals(((BTNode<E>)node.getParent()).getLeftChild()))
            return node.getParent();
        return getLastRightParent((BTNode<E>) node.getParent());
    }
}
