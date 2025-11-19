package org.example;
public class MyBinaryTree<K extends Comparable<K>> {
    MyBinaryTree<K> left;
    MyBinaryTree<K> right;
    K t;

    public MyBinaryTree(K t) {
        this.t = t;
    }

    // Insert a value into the BST
    public void insert(K value) {
        int cmp = value.compareTo(this.t);

        if (cmp <= 0) { // go left
            if (this.left == null) {
                this.left = new MyBinaryTree<>(value);
            } else {
                this.left.insert(value);
            }
        } else { // go right
            if (this.right == null) {
                this.right = new MyBinaryTree<>(value);
            } else {
                this.right.insert(value);
            }
        }
    }

    // Build tree from array
    public static <K extends Comparable<K>> MyBinaryTree<K> fromArray(K[] arr) {
        if (arr == null || arr.length == 0) return null;

        MyBinaryTree<K> root = new MyBinaryTree<>(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            root.insert(arr[i]);
        }
        return root;
    }

    // Pretty toString (rotated 90 degrees)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(this, sb, 0);
        return sb.toString();
    }

    private void buildString(MyBinaryTree<K> node, StringBuilder sb, int depth) {
        if (node == null) return;

        // print right subtree (top)
        buildString(node.right, sb, depth + 1);

        // indent
        for (int i = 0; i < depth; i++) {
            sb.append("    ");
        }

        // node value
        sb.append(node.t).append("\n");

        // print left subtree (bottom)
        buildString(node.left, sb, depth + 1);
    }

    // Demo main: process an array
    public static void main(String[] args) {
        // Example with Integer[]
        Integer[] values = {5, 2, 10, 1, 3, 8, 15};

        MyBinaryTree<Integer> root = MyBinaryTree.fromArray(values);

        System.out.println(root);
    }
}