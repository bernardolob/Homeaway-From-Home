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
        this.height = 0;
    }

    public AVLNode( E element, AVLNode<E> parent,
                    AVLNode<E> left, AVLNode<E> right ){
        super(element, parent, left, right);
        updateHeight();
    }
    public AVLNode( E element, AVLNode<E> parent){
        super(element, parent,null, null);
        this.height = 0;
    }

    public int getHeight() {
        return height;
    }

    protected void updateHeight() {
        int leftHeight = -1;
        int rightHeight = -1;

        AVLNode<E> left = (AVLNode<E>) getLeftChild();
        AVLNode<E> right = (AVLNode<E>) getRightChild();

        if (left != null)
            leftHeight = left.getHeight();

        if (right != null)
            rightHeight = right.getHeight();

        height = 1 + Math.max(leftHeight, rightHeight);
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

    protected int balanceFactor() {
        int leftHeight = -1;
        int rightHeight = -1;

        AVLNode<E> left = (AVLNode<E>) getLeftChild();
        AVLNode<E> right = (AVLNode<E>) getRightChild();

        if (left != null)
            leftHeight = left.getHeight();
        if (right != null)
            rightHeight = right.getHeight();

        return leftHeight - rightHeight;
    }

    protected boolean isUnbalanced() {
        return Math.abs(balanceFactor()) > 1;
    }

    boolean isInternal() {
        return !(getParent()==null || (getLeftChild()==null && getRightChild()==null));
    }

    /**
     * Return the child of this node with greater height
     */
    AVLNode<E> tallerChild()  {
        int rightHeight = -1;
        int leftHeight = -1;

        if (getRightChild() != null) {rightHeight = ((AVLNode<E>)getRightChild()).getHeight();}
        if (getLeftChild() != null) {leftHeight = ((AVLNode<E>)getLeftChild()).getHeight();}

        if (leftHeight > rightHeight)
            return (AVLNode<E>) getLeftChild();
        if (leftHeight < rightHeight)
            return (AVLNode<E>) getRightChild();
        if (getParent() == null)
            return (AVLNode<E>) getRightChild();
        if (this.equals(((AVLNode<E>)getParent()).getLeftChild()))
            return (AVLNode<E>) getLeftChild();
        else
            return (AVLNode<E>) getRightChild();
    }

}
