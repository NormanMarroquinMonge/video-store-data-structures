public interface VideoCollection<E extends Identifiable>{
    void remove(E element);
    boolean remove(String name, Integer id);
    boolean contains(String name, int id);
    E get(String name, int id);
    E get(int i);
    String print();
    void add(E v);
    boolean isEmpty();
    int size();
}
