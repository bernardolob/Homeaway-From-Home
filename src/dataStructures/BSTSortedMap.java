package dataStructures;

import dataStructures.exceptions.EmptyMapException;

/**
 * Binary Search Tree Sorted Map
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class BSTSortedMap<K extends Comparable<K>,V> extends BTree<Map.Entry<K,V>> implements SortedMap<K,V>{

    /**
     * Constructor
     */
    public BSTSortedMap(){
        super();
    }
    /**
     * Returns the entry with the smallest key in the dictionary.
     *
     * @return minimum entry
     * @throws EmptyMapException Empty map
     */
    @Override
    public Entry<K, V> minEntry() {
        if (isEmpty())
            throw new EmptyMapException();
        return furtherLeftElement().getElement();
    }

    /**
     * Returns the entry with the largest key in the dictionary.
     *
     * @return maximum entry
     * @throws EmptyMapException Empty map
     */
    @Override
    public Entry<K, V> maxEntry() {
        if (isEmpty())
            throw new EmptyMapException();
        return furtherRightElement().getElement();
    }


    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     *
     * @param key whose associated value is to be returned
     * @return value of entry in the dictionary whose key is the specified key,
     * or null if the dictionary does not have an entry with that key
     */
    @Override
    public V get(K key) {
        Node<Entry<K,V>> node = getNode((BTNode<Entry<K,V>>)root,key);
        if (node!=null && key.equals(node.getElement().key()))
            return node.getElement().value();
        return null;
    }

    private BTNode<Entry<K,V>> getNode(BTNode<Entry<K,V>> node, K key) {
        if ( node == null )
            return null;
        int compResult = key.compareTo(node.getElement().key());
        if ( compResult == 0 )
            return node;
        else if ( compResult < 0 ) {
            if (node.getLeftChild() == null)
                return node;
            return getNode((BTNode<Entry<K, V>>) node.getLeftChild(), key);
        } else {
            if (node.getRightChild() == null)
                return node;
            return getNode((BTNode<Entry<K, V>>) node.getRightChild(), key);
        }
    }

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     *
     * @param key   with which the specified value is to be associated
     * @param value to be associated with the specified key
     * @return previous value associated with key,
     * or null if the dictionary does not have an entry with that key
     */
    @Override
    public V put(K key, V value) {
        BTNode<Entry<K,V>> node = this.getNode((BTNode<Entry<K, V>>) root, key);
        if ( node == null || node.getElement().key().compareTo(key) != 0 ) {
            // Key does not exist, node is "parent"
            BTNode<Entry<K,V>> newLeaf = new BTNode<>(new Entry<>(key, value));
            this.linkSubtreeInsert(newLeaf, node);
            currentSize++;
            return null;
        } else {
            V oldValue = node.getElement().value();
            node.setElement(new Entry<>(key, value));
            return oldValue;
        }
    }


    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * removes it from the dictionary and returns its value;
     * otherwise, returns null.
     *
     * @param key whose entry is to be removed from the map
     * @return previous value associated with key,
     * or null if the dictionary does not an entry with that key
     */
    @Override
    public V remove(K key) {
        BTNode<Entry<K,V>> node = this.getNode((BTNode<Entry<K, V>>) root, key);
        if (node == null || node.getElement().key().compareTo(key) != 0)
            return null;
        V oldValue = node.getElement().value();
        if ( node.getLeftChild() == null )
            // The left subtree is empty.
            this.linkSubtreeRemove((BTNode<Entry<K, V>>) node.getRightChild(), (BTNode<Entry<K, V>>) node.getParent(),node);
        else if ( node.getRightChild() == null )
            // The right subtree is empty.
            this.linkSubtreeRemove((BTNode<Entry<K, V>>) node.getLeftChild(), (BTNode<Entry<K, V>>) node.getParent(),node);
        else {
            // Node has 2 children. Replace the node's entry with
            // the 'minEntry' of the right subtree.
            BTNode<Entry<K,V>> minNode = ((BTNode<Entry<K, V>>)node.getRightChild()).furtherLeftElement();
            node.setElement(minNode.getElement());
            // Remove the 'minEntry' of the right subtree.
            this.linkSubtreeRemove((BTNode<Entry<K, V>>) minNode.getRightChild(), (BTNode<Entry<K, V>>) minNode.getParent(),minNode);
        }
        currentSize--;
        return oldValue;
    }

    /**
     * Returns an iterator of the entries in the dictionary.
     *
     * @return iterator of the entries in the dictionary
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator<>((BTNode<Entry<K,V>>) root);
    }

    /**
     * Returns an iterator of the values in the dictionary.
     *
     * @return iterator of the values in the dictionary
     */
    @Override
    @SuppressWarnings({"unchecked","rawtypes"})
    public Iterator<V> values() {
        return new ValuesIterator(iterator());
    }

    /**
     * Returns an iterator of the keys in the dictionary.
     *
     * @return iterator of the keys in the dictionary
     */
    @Override
    @SuppressWarnings({"unchecked","rawtypes"})
    public Iterator<K> keys() {
        return new KeysIterator(iterator());
    }

    /**
     * Links a new subtree, rooted at the specified node, to the tree.
     *
     * @param node - root of the subtree
     * @param parent - parent node for the new subtree
     */
    private void linkSubtreeInsert(BTNode<Entry<K,V>> node, BTNode<Entry<K,V>> parent) {
        if ( parent == null )
            // Change the root of the tree.
            root = node;
        else {
            if (node != null) {
                node.setParent(parent);
                // Change child of parent.
                if (parent.getElement().key().compareTo(node.getElement().key()) >= 0)
                    parent.setLeftChild(node);
                else
                    parent.setRightChild(node);
            }
        }
    }


    /**
     * Removes a node connecting the grandchild to the parent.
     *
     * @param grandchild child of middle, to be made child of parent.
     * @param parent to be linked to grandchild, if not null.
     * @param middle node that is to be removed, child of parent, parent of grandchild
     */
    private void linkSubtreeRemove(BTNode<Entry<K,V>> grandchild, BTNode<Entry<K,V>> parent, BTNode<Entry<K,V>> middle) {
        if (parent == null) {
            // Change the root of the tree.
            if (grandchild != null)
                grandchild.setParent(null);
            root = grandchild;
        } else {
            if (grandchild != null)
                grandchild.setParent(parent);
            // Find where to replace middle with grandchild as new child of parent
            if (middle == parent.getLeftChild())
                parent.setLeftChild(grandchild);
            else
                parent.setRightChild(grandchild);
        }
    }


}
