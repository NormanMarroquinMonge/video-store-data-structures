public class SLL<E extends Comparable<E> & Identifiable> implements LL<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.element = data;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public SLL() {
        head = null;
        tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E element) {
        head = new Node<>(element, head);
        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newest = new Node<>(element, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }//End of add method

    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        head = head.getNext();
        size--;

        if (size == 0) {
            tail = null;
        }
    }//End of remove method.

    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<E> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            tail = current;
            tail.setNext(null);
        }
        size--;
    }

    public E remove(String name, Integer id) {
        if (!isEmpty()) {
            E headElement = head.getElement();
            if (headElement.getName().equals(name) && headElement.getID().equals(id)) {
                head = head.getNext();
                size--;
                if (size == 0) {
                    tail = null;
                }
                return headElement;
            } else {
                Node<E> curr = head;
                while (curr.getNext() != null) {
                    E element = curr.getNext().getElement();
                    if (element.getName().equals(name) && element.getID().equals(id)) {
                        curr.setNext(curr.getNext().getNext());
                        size--;
                        if (curr.getNext() == null) {
                            tail = curr;
                        }
                        return element;
                    }
                    curr = curr.getNext();
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public void remove(E e) {
        if (!isEmpty()) {
            E headElement = head.getElement();
            if (headElement.equals(e)) {
                head = head.getNext();
                size--;
                if (size == 0) {
                    tail = null;
                }
            } else {
                Node<E> curr = head;
                while (curr.getNext() != null) {
                    E element = curr.getNext().getElement();
                    if (element.equals(e)) {
                        curr.setNext(curr.getNext().getNext());
                        size--;
                        if (curr.getNext() == null) {
                            tail = curr;
                        }
                        return;
                    }
                    curr = curr.getNext();
                }
            }
        }
    }

    @Override
    public E get(String name, int id) {
        Node<E> curr = head;
        while (curr != null){
            E e = curr.getElement();
            if (e.getName().equals(name) && e.getID().equals(id)) {
                return e;
            }
            curr = curr.getNext();
        }
        return null;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index not within the list range");
        }

        Node<E> curr = head;
        for(int i = 0; i < index; i++){
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    public boolean contains(String name, int id) {
        Node<E> curr = head;
        while (curr != null) {
            E e = curr.getElement();
            if (e.getName().equals(name) && e.getID().equals(id)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    @Override
    public void add(E e){
        addLast(e);
    }

    public String print() {
        if (isEmpty()) {
            return "EMPTY \n";
        } else {
            StringBuilder str = new StringBuilder(head.getElement() + "\n");
            Node<E> curr = head.getNext();
            while (curr != null) {
                str.append(curr.getElement()).append("\n");
                curr = curr.getNext();
            }
            return str.toString();
        }
    }//End of print method

    @Override
    public void printSideWays() {
        System.out.println("Linear list structure (SLL):");
        System.out.println(print());
    }
}
