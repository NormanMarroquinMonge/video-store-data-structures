public class BSTree<E extends Comparable<E>> implements BT<E> {
    public BSTreeNode<E> overallRoot;

    public BSTree(){
        overallRoot = null;
    }

    //Add method seen by the clients.
    public void add(E value){
        overallRoot = add(overallRoot, value);
    }

    //Helper method that adds a node to the tree using BST property.
    private BSTreeNode<E> add(BSTreeNode<E> root, E value){
        if(root == null){
            root = new BSTreeNode<>(value);
        } else if (value.compareTo(root.data) <= 0){
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }//End of add method.

    //remove method seen by the client
    public void remove(E value){
        overallRoot = remove(overallRoot, value);
    }

    //Helper method that performs removal and updates the structure of the tree.
    private BSTreeNode<E> remove(BSTreeNode<E> root, E value){
        if(root == null){
            return null;
        } else {
            int val = value.compareTo(root.data);
            if(val < 0){
                root.left = remove(root.left, value);
            } else if (val > 0){
                root.right = remove(root.right, value);
            } else {
                if(root.left == null){
                    return root.right;
                } else if (root.right == null){
                    return root.left;
                } else {
                    root.data = getMin(root.right).data;
                    removeMin(root.right);
                }
            }
            return root;
        }
    }

    public BSTreeNode<E> getMin(){
        return getMin(overallRoot);
    }

    private BSTreeNode<E> getMin(BSTreeNode<E> root){
        if(root == null){
            return null;
        } else if (root.left == null){
            return null;
        }
        return getMin(root.left);
    }

    public void removeMin(){
        overallRoot = removeMin(overallRoot);
    }

    private BSTreeNode<E> removeMin(BSTreeNode<E> root){
        if(root == null){
            return null;
        } else if (root.left == null){
            return null;
        } else {
            root.left = removeMin(root.left);
        }
        return root;
    }

    public boolean contains(E value){
        return contains(overallRoot, value);
    }

    private boolean contains(BSTreeNode<E> root, E value){
        if (root == null){
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if(compare == 0){
                return true;
            }else if (compare < 0){
                return contains(root.left, value);
            } else{
                return contains(root.right, value);
            }
        }
    }


    public void printInOrder(){
            System.out.println();
            printInOrder(overallRoot);
            System.out.println();
    }

    private void printInOrder(BSTreeNode<E> root){
        if(root != null){
            printInOrder(root.left);
            System.out.print(root.data + " ");
            printInOrder(root.right);
        }
    }

    public void printPreOrder(){
        System.out.println();
        printPreOrder(overallRoot);
        System.out.println();
    }

    private void printPreOrder(BSTreeNode<E> root){
        if(root != null){
            System.out.print(root.data + " ");
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public void printPostOrder(){
        System.out.println();
        printPostOrder(overallRoot);
        System.out.println();
    }

    private void printPostOrder(BSTreeNode<E> root){
        if(root != null){
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print(root.data + " ");
        }
    }


    public void printSideWays(){
        printSideWays(overallRoot, 0);
    }

    private void printSideWays(BSTreeNode<E> root, int level){
        if(root != null){
            printSideWays(root.right, level + 1);
            for(int i = 0; i < level; i++){
                System.out.print("       ");
            }

            System.out.println(root.data);
            printSideWays(root.left, level + 1);
        }
    }

    public int size(){
        return size(overallRoot);
    }

    private int size(BSTreeNode<E> root){
        if(root == null){
            return 0;
        }

        return 1 + size(root.left) + size(root.right);
    }

}
