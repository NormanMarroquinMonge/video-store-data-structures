public interface LL<E extends Identifiable> {
    void addLast(E element);
    void addFirst(E element);
    void removeFirst();
    boolean remove(String name, Integer id);
    boolean remove(E e);
    E get(String name, Integer id);
    E get(int i);
    boolean contains(String name, Integer id);
    String print();
    int size();
    boolean isEmpty();
}
