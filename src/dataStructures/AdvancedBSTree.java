package dataStructures;
/**
 * Advanced Binary Search Tree
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
abstract class AdvancedBSTree <K extends Comparable<K>,V> extends BSTSortedMap<K,V>{
      /**
 	* Performs a single left rotation rooted at z node.
 	* Node y was a  right  child  of z before the  rotation,
 	* then z becomes the left child of y after the rotation.
 	* @param z - root of the rotation
	 * @pre: z has a right child
 	*/
	protected void rotateLeft( BTNode<Entry<K,V>> z){
   	 //TODO: Left as an exercise.
   	 //  a single rotation modifies a constant number of parent-child relationships,
    	// it can be implemented in O(1)time
        BTNode<Entry<K,V>> y = (BTNode<Entry<K, V>>) z.getRightChild();
        BTNode<Entry<K,V>> t2 = (BTNode<Entry<K, V>>) y.getLeftChild();
        rotate(z, y, t2);
    }

    /**
     * Performs a single right rotation rooted at z node.
     * Node y was a left  child  of z before the  rotation,
     * then z becomes the right child of y after the rotation.
     * @param z - root of the rotation
     * @pre: z has a left child
     */
    protected void rotateRight( BTNode<Entry<K,V>> z){
        //TODO: Left as an exercise.
        //  a single rotation modifies a constant number of parent-child relationships,
        // it can be implemented in O(1)time
        BTNode<Entry<K,V>> y = (BTNode<Entry<K, V>>) z.getLeftChild();
        BTNode<Entry<K,V>> t2 = (BTNode<Entry<K, V>>) y.getRightChild();
        rotate(z, y, t2);
    }

    /**
     * Performs a single rotation rooted at z node.
     * @param z root node
     * @param y child of z
     * @param t2 child of y
     */
    private void rotate(BTNode<Entry<K, V>> z, BTNode<Entry<K, V>> y, BTNode<Entry<K, V>> t2) {
        BTNode<Entry<K,V>> zOldParent = (BTNode<Entry<K, V>>) z.getParent();
        linkSubtreeInsert(t2, z);
        y.setParent(zOldParent);
        if (zOldParent != null){
            if (zOldParent.getLeftChild().equals(z))
                zOldParent.setLeftChild(y);
            else
                zOldParent.setRightChild(y);
        }
        linkSubtreeInsert(z, y);
    }

    /**
     * Performs a tri-node restructuring (a single or double rotation rooted at X node).
     * Assumes the nodes are in one of following configurations:
     *
     * @param x - root of the rotation
     * <pre>
     *          z=c       z=c        z=a         z=a
     *          /  \      /  \       /  \        /  \
     *        y=b  t4   y=a  t4    t1  y=c     t1  y=b
     *       /  \      /  \           /  \         /  \
     *     x=a  t3    t1 x=b        x=b  t4       t2 x=c
     *    /  \          /  \       /  \             /  \
     *   t1  t2        t2  t3     t2  t3           t3  t4
     * </pre>
     * @return the new root of the restructured subtree
     */
    protected BTNode<Entry<K,V>> restructure (BTNode<Entry<K,V>> x) {
        //TODO: Left as an exercise.
        // the modification of a tree T caused by a trinode restructuring operation
        // can be implemented through case analysis either as a single rotation or as a double rotation.
        // The double rotation arises when position x has the middle of the three relevant keys
        // and is first rotated above its parent Y, and then above what was originally its grandparent Z.
        // In any of the cases, the trinode restructuring is completed with O(1)running time
        BTNode<Entry<K,V>> y = (BTNode<Entry<K, V>>) x.getParent();
        BTNode<Entry<K,V>> z = (BTNode<Entry<K, V>>) y.getParent();
        BTNode<Entry<K,V>> newRoot;

        if (z.getLeftChild().equals(y)) {
            if (y.getLeftChild().equals(x)) { // caso 1
                rotateRight(z);
                newRoot = y;
            } else {                     // caso 2
                rotateLeft(y);
                rotateRight(z);
                newRoot = x;
            }
        } else {
            if (y.getLeftChild().equals(x)) { // caso 3
                rotateRight(y);
                rotateLeft(z);
                newRoot = x;
            } else {                     // caso 4
                rotateLeft(z);
                newRoot = y;
            }
        }
        return newRoot;
    }
}
