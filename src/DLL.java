
public class DLL<E extends Comparable<E> & Identifiable> implements LL<E>{
    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev){
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public E getElement(){
            return element;
        }

        public Node<E> getNext(){
            return next;
        }

        public Node<E> getPrev(){
            return prev;
        }

        public void setElement(E element){
            this.element = element;
        }

        public void setNext(Node<E> next){
            this.next = next;
        }

        public void setPrev(Node<E> prev){
            this.prev = prev;
        }
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size = 0;

    public DLL() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, null, null);
        header.setNext(trailer);
        trailer.setPrev(header);
    }

    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}


    public void addFirst(E element) {
        addInBetween(element, header, header.getNext());
    }//End of addFirst Method.

    @Override
    public void addLast(E element) {
        addInBetween(element, trailer.getPrev(), trailer);
    }//End of addLast method.

    private void addInBetween(E e, Node<E> predecessor, Node<E> successor){
        Node<E> newest = new Node<>(e, successor, predecessor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    public void removeFirst(){
        removeNode(header.getNext());
    }

    public void removeLast(){
        removeNode(trailer.getPrev());
    }

    private void removeNode(Node<E> node){
        if (node == header || node == trailer) {
            throw new IllegalArgumentException("List is empty");
        }
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
    }

    @Override
    public boolean remove(String name, Integer id){
        if (!isEmpty()) {
            E headElement = header.getNext().getElement();
            if (headElement.getName().equals(name) && headElement.getID().equals(id)) {
                removeFirst();
                return true;
            } else {
              Node<E> curr = header.getNext().getNext();
              while(curr != trailer){
                  E element = curr.getElement();
                  if(element.getName().equals(name) && element.getID().equals(id)){
                      removeNode(curr);
                      return true;
                  }
                  curr = curr.getNext();
              }
            }
        }
        return false;
    }

    @Override
    public void remove(E e) {
        if (!isEmpty()) {
            E headElement = header.getNext().getElement();
            if (headElement.equals(e)) {
                removeFirst();
            } else {
                Node<E> curr = header.getNext().getNext();
                while(curr != trailer){
                    E element = curr.getElement();
                    if(element.equals(e)){
                        removeNode(curr);
                        return;
                    }
                    curr = curr.getNext();
                }
            }
        }
    }

    @Override
    public E get(String name, int id) {
        Node<E> current = header.getNext();
        while(current != trailer){
            E e = current.getElement();
            if(e.getName().equals(name) && e.getID().equals(id)){
                return e;
            }
            current = current.getNext();
        }

        return null;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index is not within the list range");
        }

        Node<E> curr = header.getNext();
        for(int i = 0; i < index; i++){
         curr = curr.getNext();
        }

        return curr.getElement();
    }

    public boolean contains(String name, int id){
        Node<E> current = header.getNext();
        while(current != trailer){
            E e = current.getElement();
            if(e.getName().equals(name) && e.getID().equals(id)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void add(E e){
        addLast(e);
    }

    public String print() {
        if (header.getNext() == trailer) {
            return "EMPTY \n";
        } else {
            Node<E> current = header.getNext();
            StringBuilder str = new StringBuilder(current.getElement() + "\n");
            while (current.getNext() != trailer) {
                current = current.getNext();
                str.append(current.getElement()).append("\n");
            }
            return str.toString();
        }
    }//End of print method.

    @Override
    public void printSideWays() {
        System.out.println("Linear list structure (DLL):");
        System.out.println(print());
    }
}//End of DLL class.
