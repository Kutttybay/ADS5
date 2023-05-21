import java.util.ArrayList;
public class BST<K extends Comparable<K>,V> {
    private Node root;
    public class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    private int size = 0;

    public int getSize(){
        return size;
    }
    private Node insertNode(Node node, K key, V value){
        if (node == null){
            return new Node(key, value);
        }
        if (node.key.compareTo(key) == 1) {
            node.left = insertNode(node.left, key, value);
        } else if(node.key.compareTo(key) == -1) {
            node.right = insertNode(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }
    public void put(K key,V value){
        this.root = insertNode(root, key, value);
        size++;
    }


}
