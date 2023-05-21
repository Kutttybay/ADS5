import java.util.ArrayList;
import java.util.Iterator;

public class BST<K extends Comparable<K>,V> {

    // Вложенный класс Node представляет узел дерева. Он содержит ключ, значение и ссылки на левого и правого потомков.
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
    /*
    *  метод рекурсивно вставляет новый узел в дерево. Если узел равен null, создается новый узел с указанным ключом и значением.
    *  Если ключ меньше текущего узла, происходит рекурсивный вызов для левого поддерева, иначе для правого.
    *  Если ключ уже существует, обновляется значение.
    */
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

    /*
    * метод рекурсивно находит узел с указанным ключом в дереве. Если текущий узел равен null или ключи совпадают, возвращается текущий узел.
    * В противном случае происходит рекурсивный вызов для соответствующего поддерева.
    */
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

    // метод возвращает значение, связанное с указанным ключом, или null, если ключ не найден.
    public V get(K key){
        Node node = getTreeNode(root,key);
        return (node.equals(null) ? null : node.value);
    }

    /*
    * метод рекурсивно удаляет узел с указанным ключом из дерева. Если текущий узел равен null, возвращается null.
    *  Если ключ меньше текущего узла, происходит рекурсивный вызов для левого поддерева, иначе для правого.
    *  Если найден узел для удаления, происходит проверка его потомков. Если узел является листом, он удаляется.
    *  Если у узла отсутствует левый или правый потомок, соответствующая ссылка заменяется на существующего потомка.
    *  Если у узла есть оба потомка, находится минимальный узел в правом поддереве и его ключ и значение копируются в текущий узел.
    *  Затем происходит рекурсивное удаление минимального узла из правого поддерева.
    */
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

    // метод удаляет узел с указанным ключом из дерева и уменьшает размер дерева.
    public void delete(K key){
        this.root = deleteNode(root, key);
        size--;
    }

    // метод находит узел с минимальным ключом в дереве путем итеративного спуска влево.
    private Node findMinimumNode(Node node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    /*
    * метод выполняет обход дерева в порядке "in-order" (левый потомок, узел, правый потомок) и добавляет узлы в переданный список list.
    *  Метод также рекурсивно вызывает сам себя для левого и правого поддеревьев.
    */
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
    /*
    * метод возвращает итератор для обхода элементов дерева в порядке "in-order".
    * Он вызывает inOrderTraversal для получения списка узлов, который затем приводится к типу Iterable и возвращается.
    */
    public Iterable<Node> iterator(){
        ArrayList<Node> arr = inOrderTraversal(new ArrayList<>(), root);
        return (Iterable) arr;
    }


}
