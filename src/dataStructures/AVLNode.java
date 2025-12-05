package dataStructures;
/**
 * AVL Tree Node
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */
class AVLNode<E> extends BTNode<E> {
    // Height of the node
    protected int height;

    public AVLNode(E elem) {
        super(elem);
        updateHeight();
    }

    public AVLNode( E element, AVLNode<E> parent,
                    AVLNode<E> left, AVLNode<E> right ){
        super(element, parent, left, right);
        updateHeight();
    }
    public AVLNode( E element, AVLNode<E> parent){
        super(element, parent,null, null);
        updateHeight();
    }

    public int getHeight() {
        return height;
    }
    private void updateHeight() {
        height = super.getHeight();
    }

    /**
     * Update the left child and height
     * @param node node
     */
    public void setLeftChild(AVLNode<E> node) {
        super.setLeftChild(node);
        updateHeight();
    }

    /**
     * Update the right child and height
     * @param node node
     */
    public void setRightChild(AVLNode<E> node) {
        super.setRightChild(node);
        updateHeight();
    }
// others public methods
//TODO: Left as an exercise.
    protected boolean isUnbalanced() {
        return Math.abs(((AVLNode<E>) getLeftChild()).getHeight() - ((AVLNode<E>) getRightChild()).getHeight()) > 1;
    }

}
