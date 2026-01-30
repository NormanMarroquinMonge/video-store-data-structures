public interface Tree<E extends Identifiable> extends VideoCollection<E>{
    void add(E element);
    void remove(E element);
    E remove(String name, Integer id);
    int size();
    boolean contains(E element);
    boolean contains(String name, int id);
    E get(String name, int id);
    void printSideWays();
    void printInOrder();
    void printPreOrder();
    void printPostOrder();
}
