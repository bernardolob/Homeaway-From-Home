package dataStructures;

import dataStructures.exceptions.NoSuchElementException;


/**
 * Sorted Doubly linked list Implementation
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class SortedDoublyLinkedList<E> implements SortedList<E> {

    /**
     *  Node at the head of the list.
     */
    private DoublyListNode<E> head;
    /**
     * Node at the tail of the list.
     */
    private DoublyListNode<E> tail;
    /**
     * Number of elements in the list.
     */
    private int currentSize;
    /**
     * Comparator of elements.
     */
    private final Comparator<E> comparator;
    /**
     * Constructor of an empty sorted double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public SortedDoublyLinkedList(Comparator<E> comparator) {
        this.comparator = comparator;
        head = null;
        tail = null;
        currentSize = 0;
    }

    /**
     * Returns true iff the list contains no elements.
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return currentSize==0;
    }

    /**
     * Returns the number of elements in the list.
     * @return number of elements in the list
     */

    public int size() {
        return currentSize;
    }

    /**
     * Returns an iterator of the elements in the list (in proper sequence).
     * @return Iterator of the elements in the list
     */
    public Iterator<E> iterator() {
        return new DoublyIterator<>(head);
    }

    /**
     * Returns the first element of the list.
     * @return first element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E getMin( ) {
        if (isEmpty())
            throw new NoSuchElementException();
        return head.getElement();
    }

    /**
     * Returns the last element of the list.
     * @return last element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E getMax( ) {
        if (isEmpty())
            throw new NoSuchElementException();
        return tail.getElement();
    }
    /**
     * Returns the first occurrence of the element equals to the given element in the list.
     * @return element in the list or null
     */
    public E get(E element) {
        DoublyListNode<E> n = getNode(element);
        if (n == null)
            return null;
        else return n.getElement();
    }

    /**
     * Returns true iff the element exists in the list.
     *
     * @param element to be found
     * @return true iff the element exists in the list.
     */
    public boolean contains(E element) {
        //TODO: Left as an exercise.
        return get(element) != null;
    }

    /**
     * Inserts the specified element at the list, according to the natural order.
     * If there is an equal element, the new element is inserted after it.
     * @param element to be inserted
     */
    public void add(E element) {
        if (isEmpty()) {
            addMin(element);
        } else {
            if (comparator.compare(head.getElement(), element) > 0)
                addMin(element);
            else if (comparator.compare(tail.getElement(), element) < 0) {
                addMax(element);
            } else {
                DoublyListNode<E> comparingNode = head.getNext();
                while (comparator.compare(comparingNode.getElement(), element) <= 0)
                    comparingNode = comparingNode.getNext();
                addBeforeNode(element, comparingNode);
            }
        }
    }

    /**
     * Adds a node before another.
     * @param element - to be inserted
     * @param node - node after the new node to be inserted.
     */
    private void addBeforeNode(E element, DoublyListNode<E> node) {
        DoublyListNode<E> newNode = new DoublyListNode<>(element, node.getPrevious(), node);
        node.getPrevious().setNext(newNode);
        node.setPrevious(newNode);
        currentSize++;
    }

    /**
     * Adds the minimum node.
     * @param element - to be inserted
     */
    private void addMin(E element) {
        DoublyListNode<E> newNode = new DoublyListNode<>(element);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.setPrevious(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        currentSize++;
    }

    /**
     * Adds the maximum node.
     * @param element - to be inserted
     */
    private void addMax(E element) {
        DoublyListNode<E> newNode = new DoublyListNode<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        tail = newNode;
        currentSize++;
    }

    /**
     * Removes and returns the first occurrence of the element equals to the given element in the list.
     * @return element removed from the list or null if !belongs(element)
     */
    public E remove(E element) {
        DoublyListNode<E> node = getNode(element);
        if (node == null)
            return null;
        E result = node.getElement();
        DoublyListNode<E> prev = node.getPrevious();
        DoublyListNode<E> next = node.getNext();
        if (prev != null)
            prev.setNext(next);
        if (next != null)
            next.setPrevious(prev);
        if (node == head)
            head = next;
        if (node == tail)
            tail = prev;
        currentSize--;
        return result;
    }


    /**
     * Returns the first occurrence of the element equals to the given element in the list.
     * @return element in the list or null
     */
    private DoublyListNode<E> getNode(E element) {
        if (isEmpty())
            return null;
        DoublyListNode<E> node = head;
        while (node != null) {
            if (comparator.compare(node.getElement(), element) == 0)
                return node;
            node = node.getNext();
        }
        return null;
    }
}
