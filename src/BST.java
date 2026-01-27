public abstract class BST<E extends Comparable<E> & Identifiable> implements Tree<E> {
    protected static class Node<E> {
        public E data;
        public Node<E> left;
        public Node<E> right;
        public int height;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            height = 0;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return height;
        }
    }

    private Node<E> overallRoot;
    private int size;

    public BST() {
        size = 0;
        overallRoot = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //Add method seen by the clients.
    public void add(E value) {
        overallRoot = add(overallRoot, value);
        size++;
    }

    //Helper method that adds a node to the tree using BST property.
    private Node<E> add(Node<E> root, E value) {
        if (root == null) {
            root = new Node<>(value);
        } else if (value.compareTo(root.data) <= 0) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        root.setHeight(Math.max(height(root.left), height(root.right)) + 1);
        return restructure(root);
    }//End of add method.

    //remove method seen by the client
    public void remove(E value) {
        int overallSize = size;
        overallRoot = remove(overallRoot, value);
    }

    //Helper method that performs removal and updates the structure of the tree.
    private Node<E> remove(Node<E> root, E value) {
        if (root == null) {
            return null;
        } else {
            int val = value.compareTo(root.data);
            if (val < 0) {
                root.left = remove(root.left, value);
            } else if (val > 0) {
                root.right = remove(root.right, value);
            } else {
                size--;
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    root.data = getMin(root.right).data;
                    root.right = removeMin(root.right);
                }
            }
            root.setHeight(Math.max(height(root.left), height(root.right)) + 1);
            return restructure(root);
        }
    }

    @Override
    public boolean remove(String name, Integer id) {
        int previousSize = size;
        overallRoot = remove(overallRoot, name, id);
        return previousSize > size;
    }

    private Node<E> remove(Node<E> root, String name, Integer id) {
        if (root == null) {
            return null;
        } else {
            E element = root.data;
            int val = element.getID().compareTo(id);
            if (val > 0) {
                root.left = remove(root.left, name, id);
            } else if (val < 0) {
                root.right = remove(root.right, name, id);
            } else {
                if (element.getName().equals(name)) {
                    size--;
                    if (root.left == null) {
                        return root.right;
                    } else if (root.right == null) {
                        return root.left;
                    } else {
                        root.data = getMin(root.right).data;
                        root.right = removeMin(root.right);
                    }
                }
            }
            root.setHeight(Math.max(height(root.left), height(root.right)) + 1);
            return restructure(root);
        }
    }

    private Node<E> getMin() {
        return getMin(overallRoot);
    }

    private Node<E> getMin(Node<E> root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root;
        }
        return getMin(root.left);
    }

    private void removeMin() {
        overallRoot = removeMin(overallRoot);
    }

    private Node<E> removeMin(Node<E> root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root.right;
        } else {
            root.left = removeMin(root.left);
        }
        return root;
    }

    public boolean contains(E value) {
        return contains(overallRoot, value);
    }

    @Override
    public boolean contains(String name, int id) {
        return contains(overallRoot, name, id);
    }

    @Override
    public String print() {
        String result = print(overallRoot).trim();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1); // remove trailing comma
        }
        return "[" + result + "]";
    }

    private String print(Node<E> root) {
        if (root == null) {
            return "";
        }

        // Inorder traversal
        String left = print(root.left);
        String current = root.data + ", ";
        String right = print(root.right);

        return left + current + right;
    }

    @Override
    public E get(String name, int id) {
        return get(overallRoot, name, id);
    }

    private E get(Node<E> root, String name, int id) {
        if (root == null) {
            return null;
        } else {
            int val = root.data.getID().compareTo(id);
            if (val > 0) {
                return get(root.left, name, id);
            } else if (val < 0) {
                return get(root.right, name, id);
            } else {
                if (root.data.getName().equals(name)) {
                    return root.data;
                }
                return null;
            }
        }
    }

    @Override
    public E get(int id) {
        return get(overallRoot, id);
    }

    private E get(Node<E> root, int id) {
        if (root == null) {
            return null;
        } else {
            int val = root.data.getID().compareTo(id);
            if (val > 0) {
                return get(root.left, id);
            } else if (val < 0) {
                return get(root.right, id);
            } else {
                return root.data;
            }
        }
    }

    private boolean contains(Node<E> root, String name, int id) {
        if (root == null) {
            return false;
        } else {
            int val = root.data.getID().compareTo(id);
            if (val > 0) {
                return contains(root.left, name, id);
            } else if (val < 0) {
                return contains(root.right, name, id);
            } else {
                return true;
            }
        }
    }

    private boolean contains(Node<E> root, E value) {
        if (root == null) {
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(root.left, value);
            } else {
                return contains(root.right, value);
            }
        }
    }

    protected Node<E> restructure(Node<E> n){
        return n;
    }

    protected int height(Node<E> n){
        return (n == null) ? -1 : n.getHeight();
    }

    public void printInOrder() {
        System.out.println();
        printInOrder(overallRoot);
        System.out.println();
    }

    private void printInOrder(Node<E> root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.data + " ");
            printInOrder(root.right);
        }
    }

    public void printPreOrder() {
        System.out.println();
        printPreOrder(overallRoot);
        System.out.println();
    }

    private void printPreOrder(Node<E> root) {
        if (root != null) {
            System.out.print(root.data + " ");
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public void printPostOrder() {
        System.out.println();
        printPostOrder(overallRoot);
        System.out.println();
    }

    private void printPostOrder(Node<E> root) {
        if (root != null) {
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print(root.data + " ");
        }
    }


    public void printSideWays() {
        printSideWays(overallRoot, 0);
    }

    private void printSideWays(Node<E> root, int level) {
        if (root != null) {
            printSideWays(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("       ");
            }

            System.out.println(root.data);
            printSideWays(root.left, level + 1);
        }
    }

    public int size() {
        return size;
    }
}
