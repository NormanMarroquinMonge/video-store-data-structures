public interface LL<E extends Identifiable> extends VideoCollection<E> {
    void addLast(E element);
    void addFirst(E element);
    void removeFirst();
    E remove(String name, Integer id);
    void remove(E e);
    E get(String name, int id);
    E get(int i);
    boolean contains(String name, int id);
    String print();
    int size();
    boolean isEmpty();
}
