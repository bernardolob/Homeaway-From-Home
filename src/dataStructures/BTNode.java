package dataStructures;

/**
 * Binary Tree Node
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */
class BTNode<E> implements Node<E> {
    // Element stored in the node.
    private E element;

    // (Pointer to) the father.
    private Node<E> parent;

    // (Pointer to) the left child.
    private Node<E> leftChild;

    // (Pointer to) the right child.
    private Node<E> rightChild;

    /**
     * Constructor
     * @param elem new element
     */
    BTNode(E elem){
        this(elem,null,null,null);
    }

    /**
     * Constructor
     * @param elem new element
     * @param parent node
     */
    BTNode(E elem, BTNode<E> parent) {
        this(elem,parent,null,null);
    }
    /**
     * Constructor
     * @param elem new element
     * @param parent node
     * @param leftChild node
     * @param rightChild node
     */
    BTNode(E elem, BTNode<E> parent, BTNode<E> leftChild, BTNode<E> rightChild){
        this.element = elem;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     *  Returns the element of the node
     * @return node element
     */
    public E getElement() {
        return element;
    }
    /**
     * Returns the left son of node
     * @return left child
     */
    public Node<E> getLeftChild(){
        return leftChild;
    }
    /**
     * Returns the right son of node
     * @return right child
     */
    public Node<E> getRightChild(){
        return rightChild;
    }
    /**
     * Returns the parent of node
     * @return parent
     */
    public Node<E> getParent(){
        return parent;
    }

    /**
     * Returns true if node n does not have any children.
     * @return if is a leaf
     */
    boolean isLeaf() {
        return getLeftChild()== null && getRightChild()==null;
    }

    /**
     * Update the element
     * @param elem new element
     */
    public void setElement(E elem) {
        element=elem;
    }

    /**
     * Update the left child
     * @param node left child node
     */
    public void setLeftChild(Node<E> node) {
        leftChild=node;
    }

    /**
     * Update the right child
     * @param node right child node
     */
    public void setRightChild(Node<E> node) {
        rightChild=node;
    }

    /**
     * Update the parent
     * @param node parent node
     */
    public void setParent(Node<E> node) {
        parent=node;
    }

    /**
     * Returns true if is the root
     */
    boolean isRoot() {
        return getParent()==null;
    }

    /**
     * Returns the height of the subtree rooted at this node.
     */
    public int getHeight() {
        if (isLeaf())
            return 0;
        BTNode<E> left= (BTNode<E>)getLeftChild();
        BTNode<E> right= (BTNode<E>)getRightChild();
        if (left==null)
            return 1+right.getHeight();
        if (right == null)
            return 1+left.getHeight();
        return 1 + Math.max(left.getHeight(),right.getHeight());
    }

    /**
     *
     * @return the furthermost left element of the tree.
     */
    BTNode<E> furtherLeftElement() {
        BTNode<E> node = this;
        while(node.leftChild!=null){
            node = (BTNode<E>) node.leftChild;
        }
        return node;
    }

    /**
     *
     * @return the furthermost right element of the tree.
     */
    BTNode<E> furtherRightElement() {
        BTNode<E> node = this;
        while(node.rightChild!=null){
            node = (BTNode<E>) node.rightChild;
        }
        return node;
    }

}
