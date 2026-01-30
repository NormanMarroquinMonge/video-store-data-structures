public class BSTTree<E extends Comparable<E> & Identifiable> extends BST<E> {
    public BSTTree() {
        super();
    }
        public static void main(String[] args){
       Tree<Video> tree = new BSTTree<>();
       Video v = new Video("Test1", 20);
       Video v2 = new Video("Test2", 15);
       Video v3 = new Video("Test3", 13);
       Video v4 = new Video("Test4", 40);
       Video v5 = new Video("Test5", 32);
       Video v6 = new Video("Test6", 55);
       Video v7 = new Video("test7", 19);
       Video v8 = new Video("test8", 25);
       Video v9 = new Video("test9", 22);
       Video v10 = new Video("test10", 24);
       Video v11 = new Video("Test", 30);
       tree.add(v); tree.add(v2); tree.add(v3); tree.add(v4); tree.add(v5); tree.add(v6); tree.add(v7); tree.add(v8);
       tree.add(v9); tree.add(v10); tree.add(v11);
       tree.printSideWays();
       tree.remove(v6);
       tree.remove(v4);
       System.out.println();
       tree.printSideWays();
}
}
