public class BSTreeNode<E> {
    public E data;
    public BSTreeNode<E> left;
    public BSTreeNode<E> right;

    public BSTreeNode(E data){
       this(data, null, null);
    }

    public BSTreeNode(E data, BSTreeNode<E> left, BSTreeNode<E> right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
