# ADS Assaignment 5 K.D.E Se-2216
# $\S1.$ BST 
## Node 

``` java
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
```
Description : This code defines a binary tree data structure. It includes a private instance variable root representing the root node.<br/>
The Node class represents a node in the binary tree and has instance variables key and value to store a key-value pair.<br/>
Each node has references to its left and right child nodes. The Node class also overrides the toString() method to provide a string representation of the node, displaying its key-value pair.<br/>
<hr/>
## getSize

``` java
    private int size = 0;

    public int getSize(){
        return size;
    }
```
<br/>
Description : return size of the binary search tree
<hr/>
## put

``` java
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
    // метод вставляет новый узел в дерево и увеличивает размер дерева.
    public void put(K key,V value){
        this.root = insertNode(root, key, value);
        size++;
    }
```

Description : The insertNode method inserts a new node with a key-value pair into the binary tree.<br/>
It compares the key of the current node with the given key and recursively inserts the new node in the appropriate subtree.<br/>
If the key already exists, it updates the node's value. The put method inserts a key-value pair into the tree by calling insertNode on the root node.<br/>
<hr/>
## get

``` java 
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
```

Description : The getTreeNode method retrieves a node with a specific key from the binary tree.<br/>
It recursively searches for the node by comparing keys and traversing left or right based on the comparison.<br/>
The get method uses getTreeNode to find the node with the given key and returns its value if found, or null if not found.<br/>

## delete 

``` java 
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
                Node minimun_node = findMinimumNode(node);
                node.key = minimun_node.key;
                node.value = minimun_node.value;
                node.right = deleteNode(node.right, minimun_node.key);
            }
        }
        return node;
    }

        public void delete(K key){
        this.root = deleteNode(root, key);
        size--;
    }

    private Node findMinimumNode(Node node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
```

Description : The deleteNode method removes a node with a given key from the binary tree. It handles various cases depending on the existence and position of child nodes.<br/>
The delete method invokes deleteNode to delete a node with the given key from the tree. The findMinimumNode method finds the node with the smallest key in a subtree.<br/>

<hr/>
## iterator

``` java 
    private ArrayList<Node> inOrderTraversal(ArrayList list, Node node){
        if (node == null){
            return null;
        }
        if (node.left != null){
            list.add(inOrderTraversal(list, node.left));
        }
        list.add(node);
        if (node.right != null){
            list.add(inOrderTraversal(list, node.right));
        }
        return list;
    }

        public Iterable<Node> iterator(){
        ArrayList<Node> arr = inOrderTraversal(new ArrayList<>(), root);
        return (Iterable) arr;
    }
```
Description : The inOrderTraversal method performs an in-order traversal of the binary tree, returning the nodes in a list. <br/>
The iterator method returns an iterable object for iterating over the nodes in the binary tree using an in-order traversal.<br/>
<hr/>
