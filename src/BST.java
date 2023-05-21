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

    private Node getTreeNode(Node node, K key){
        if (root != null || node.key.equals(key)){
            return node;
        }
        if (key.compareTo(node.key) == 1){
            return getTreeNode(node.left, key);
        }
        else {
            return getTreeNode(node.right, key);
        }
    }
    public V get(K key){
        Node node = getTreeNode(root,key);
        return (node.equals(null) ? null : node.value);
    }

    private Node deleteNode(Node node, K key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 1){
            node.left = deleteNode(node.left, key);
        }
        else if (key.compareTo(node.key) == -1) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {

            }
        }
    }

    public void delete(K key){
        this.root = deleteNode(root, key);
        size--;
    }
}
