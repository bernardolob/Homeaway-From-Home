package dataStructures;
/**
 * AVL Tree Sorted Map
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class AVLSortedMap <K extends Comparable<K>,V> extends AdvancedBSTree<K,V>{
    /**
     * Insert or update a key-value pair.
     * If key exists, update its value and return old value.
     * Otherwise insert a new node and rebalance.
     *
     * @param key k
     * @param value v
     * @return old value or null
     */
    @Override
    public V put(K key, V value) {

        BTNode<Entry<K,V>> node = getNode((BTNode<Entry<K, V>>) root, key);

        if (node == null) {
            Entry<K,V> e = new Entry<>(key, value);
            root = new AVLNode<>(e, null);
            currentSize++;
            return null;
        }

        // Search for key

        // Key exists
        if (key.equals(node.getElement().key())) {
            Entry<K,V> entry = node.getElement();
            V oldValue = entry.value();
            node.setElement(new Entry<>(key, value));
            return oldValue;
        }

        // Insert new node
        AVLNode<Entry<K,V>> newNode = new AVLNode<>(new Entry<>(key, value), (AVLNode<Entry<K,V>>) node);

        // Attach newNode as child of parent
        if (key.compareTo(node.getElement().key()) < 0)
            node.setLeftChild(newNode);
        else
            node.setRightChild(newNode);

        currentSize++;

        // Rebalance from newNode upward
        rebalance(newNode);

        return null;
    }

    /**
     * Rebalance method called by insert and remove. Traverses the path from
     * zPos to the root. For each node encountered, we recompute its height
     * and perform a trinode restructuring if it's unbalanced.
     * the rebalance is completed with O(log n) running time
     */
    void rebalance(AVLNode<Entry<K,V>> zPos) {
        if(zPos.isInternal())
            zPos.updateHeight();
        // Improve if possible...
        while (true) {  // traverse up the tree towards the root
            zPos = (AVLNode<Entry<K, V>>) zPos.getParent();
            if (zPos == null) //reached the root, stop.
                break;
            zPos.updateHeight();
            if (zPos.isUnbalanced()) {
                // perform a trinode restructuring at zPos's tallest grandchild
                //If yPos (zPos.tallerChild()) denote the child of zPos with greater height.
                //Finally, let xPos be the child of yPos with greater height
                AVLNode<Entry<K,V>> xPos = zPos.tallerChild().tallerChild();

                zPos = (AVLNode<Entry<K, V>>) restructure(xPos); // tri-node restructure (from parent class)
                ((AVLNode<Entry<K, V>>) zPos.getLeftChild()).updateHeight();  // recompute heights
                ((AVLNode<Entry<K, V>>) zPos.getRightChild()).updateHeight();
                zPos.updateHeight();
            }
        }
    }


    /**
     * Removal method for AVL
     */
    @Override
    public V remove(K key) {
        // TODO DONE
        AVLNode<Entry<K,V>> node = (AVLNode<Entry<K, V>>) getNode((BTNode<Entry<K, V>>) root, key); // father of node where the key was

        // removeNode is the BTree remove(key)
        if ( node == null || node.getElement().key().compareTo(key) != 0 )
            return null;
        else
        {
            V valueToReturn = node.getElement().value();

            if ( node.getLeftChild() == null )
                // The left subtree is empty.
                this.linkSubtreeRemove((BTNode<Entry<K, V>>) node.getRightChild(), (BTNode<Entry<K, V>>) node.getParent(),node);
            else if ( node.getRightChild() == null )
                // The right subtree is empty.
                this.linkSubtreeRemove((BTNode<Entry<K, V>>) node.getLeftChild(), (BTNode<Entry<K, V>>) node.getParent(),node);
            else
            {
                // Node has 2 children. Replace the node's entry with
                // the 'minEntry' of the right subtree.
                AVLNode<Entry<K,V>> minNode = (AVLNode<Entry<K,V>>) ((BTNode<Entry<K,V>>)node.getRightChild()).furtherLeftElement();
                node.setElement( minNode.getElement() );
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtreeRemove((BTNode<Entry<K, V>>) minNode.getRightChild(), (BTNode<Entry<K, V>>) minNode.getParent(),minNode);
            }
            currentSize--;
            // NOTA: tirei if(node!=null)
            rebalance(node); // rebalance up from the node
            return valueToReturn;
        }
    }
}
