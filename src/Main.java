public class Main {
    public static void main(String[] args) {
        System.out.println("Hello in Assignment 5");
        BST<Integer, String> bst = new BST<>();

        for (int i = 0; i < 20; i++){
            bst.put(i, "Value " + i);
        }
    }
}