public class AVLTree<E extends Comparable<E> & Identifiable> extends BST<E> {

    public AVLTree(){
        super();
    }

    private int balanceFactor(Node<E> n){
        if(n == null) return 0;
        return height(n.left) - height(n.right);
    }

    @Override
    protected Node<E> restructure(Node<E> n){
        int balanceFactor = balanceFactor(n);
        if (balanceFactor == -2) {
            if (height(n.right.right) >= height(n.right.left)) {
                return leftRotation(n);
            } else {
                return leftRightRotation(n);
            }
        }
        if (balanceFactor == 2) {
            if (height(n.left.left) >= height(n.left.right)) {
                return rightRotation(n);
            } else {
                return rightLeftRotation(n);
            }
        }
        return n;
    }

    private Node<E> leftRotation(Node<E> z){
        Node<E> y = z.right;
        z.right = y.left;
        y.left = z;
        z.setHeight(Math.max((height(z.left)), height(z.right)) + 1);
        y.setHeight(Math.max((height(y.left)), height(y.right))+1);
        return y;
    }

    private Node<E> rightRotation(Node<E> z){
        Node<E> y = z.left;
        z.left = y.right;
        y.right = z;
        z.setHeight(Math.max(height(z.left), height(z.right)) + 1);
        y.setHeight(Math.max((height(y.left)), height(y.right))+1);
        return y;
    }

    private Node<E> leftRightRotation(Node<E> z){
        Node<E> y = z.right;
        Node<E> x = y.left;
        z.right = x.left;
        x.left = z;
        y.left = x.right;
        x.right = y;
        z.setHeight(Math.max(height(z.left), height(z.right)) + 1);
        y.setHeight(Math.max(height(y.left), height(y.right)) + 1);
        x.setHeight(Math.max(height(x.left), height(x.right)) + 1);
        return x;
    }

    private Node<E> rightLeftRotation(Node<E> z){
        Node<E> y = z.left;
        Node<E> x = y.right;
        z.left = x.right;
        x.right = z;
        y.right = x.left;
        x.left = y;
        z.setHeight(Math.max(height(z.left), height(z.right)) + 1);
        y.setHeight(Math.max(height(y.left), height(y.right)) + 1);
        x.setHeight(Math.max(height(x.left), height(x.right)) + 1);
        return x;
    }

//    public static void main(String[] args){
//       Tree<Video> tree = new AVLTree<>();
//       Video v = new Video("Test1", 20);
//       Video v2 = new Video("Test2", 15);
//       Video v3 = new Video("Test3", 13);
//       Video v4 = new Video("Test4", 40);
//       Video v5 = new Video("Test5", 32);
//       Video v6 = new Video("Test6", 55);
//       Video v7 = new Video("test7", 19);
//       Video v8 = new Video("test8", 25);
//       Video v9 = new Video("test9", 22);
//       Video v10 = new Video("test10", 24);
//       Video v11 = new Video("Test", 30);
//       tree.add(v); tree.add(v2); tree.add(v3); tree.add(v4); tree.add(v5); tree.add(v6); tree.add(v7); tree.add(v8);
//       tree.add(v9); tree.add(v10); tree.add(v11);
//       tree.printSideWays();
//       tree.remove(v6);
//       tree.remove(v4);
//       System.out.println();
//       tree.printSideWays();
//
//    }
}
