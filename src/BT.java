public interface BT<E> {
    void add(E element);
    void remove(E element);
    int size();
    boolean contains(E element);
    void printSideWays();
    void printInOrder();
    void printPreOrder();
    void printPostOrder();
}
