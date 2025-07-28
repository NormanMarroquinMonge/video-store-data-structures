
public class DLL<E extends Comparable<E> & Identifiable> implements LL<E>{
    public static class DNode<E> {
        private E element;
        private DNode<E> next;
        private DNode<E> prev;

        public DNode(E element, DNode<E> next, DNode<E> prev){
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public E getElement(){
            return element;
        }

        public DNode<E> getNext(){
            return next;
        }

        public DNode<E> getPrev(){
            return prev;
        }

        public void setElement(E element){
            this.element = element;
        }

        public void setNext(DNode<E> next){
            this.next = next;
        }

        public void setPrev(DNode<E> prev){
            this.prev = prev;
        }
    }

    private DNode<E> header;
    private DNode<E> trailer;
    private int size = 0;

    public DLL() {
        header = new DNode<>(null, null, null);
        trailer = new DNode<>(null, null, null);
        header.setNext(trailer);
        trailer.setPrev(header);
    }

    public DNode<E> getHeader() {
        return header;
    }

    public DNode<E> getTrailer() {
        return trailer;
    }

    public void setHeader(DNode<E> header) {
        this.header = header;
    }

    public void setTrailer(DNode<E> trailer) {
        this.trailer = trailer;
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

    private void addInBetween(E e, DNode<E> predecessor, DNode<E> successor){
        DNode<E> newest = new DNode<>(e, successor, predecessor);
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

    private void removeNode(DNode<E> node){
        if (node == header || node == trailer) {
            throw new IllegalArgumentException("List is empty");
        }
        DNode<E> predecessor = node.getPrev();
        DNode<E> successor = node.getNext();
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
              DNode<E> curr = header.getNext().getNext();
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
    public boolean remove(E e) {
        if (!isEmpty()) {
            E headElement = header.getNext().getElement();
            if (headElement.equals(e)) {
                removeFirst();
                return true;
            } else {
                DNode<E> curr = header.getNext().getNext();
                while(curr != trailer){
                    E element = curr.getElement();
                    if(element.equals(e)){
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
    public E get(String name, Integer id) {
        DNode<E> current = header.getNext();
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

        DNode<E> curr = header.getNext();
        for(int i = 0; i < index; i++){
         curr = curr.getNext();
        }

        return curr.getElement();
    }

    public boolean contains(String name, Integer id){
        DNode<E> current = header.getNext();
        while(current != trailer){
            E e = current.getElement();
            if(e.getName().equals(name) && e.getID().equals(id)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public String print() {
        if (header.getNext() == trailer) {
            return "EMPTY \n";
        } else {
            DNode<E> current = header.getNext();
            StringBuilder str = new StringBuilder(current.getElement() + "\n");
            while (current.getNext() != trailer) {
                current = current.getNext();
                str.append(current.getElement()).append("\n");
            }
            return str.toString();
        }
    }//End of print method.
}//End of DLL class.
