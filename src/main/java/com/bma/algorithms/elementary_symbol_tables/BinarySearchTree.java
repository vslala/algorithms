package com.bma.algorithms.elementary_symbol_tables;

import com.bma.algorithms.sort.elementary.Util;

import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root;

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else if (cmp == 0)
            x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp > 0) x = x.right;
            else if (cmp < 0) x = x.left;
            else if (cmp == 0) return x.val;
        }
        return null;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key) {}

    /**
     * The key which is just smaller to the passed value
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null ) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null) return t;

        return x;
    }

    public int size() {
        return size(root) + 1;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    /**
     * Returns the number of key less than the provided key
     * Return the rank of the provided key
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0; // There are no keys in the BST

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        // cmp == 0
        else return size(x.left);
    }

    public Iterable<Key> iterator() {
        Queue<Key> q = new LinkedList<>();
        inOrder(root, q);
        return q;
    }

    private void inOrder(Node x, Queue<Key> q) {
        if (x == null ) return;
        inOrder(x.left, q);
        q.add(x.key);
        inOrder(x.right, q);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.put("B".hashCode(), "B");
        bst.put("I".hashCode(), "I");
        bst.put("N".hashCode(), "N");
        bst.put("A".hashCode(), "A");
        bst.put("R".hashCode(), "R");
        bst.put("Y".hashCode(), "Y");
        bst.put("S".hashCode(), "S");
        bst.put("E".hashCode(), "E");
        bst.put("A".hashCode(), "A");
        bst.put("R".hashCode(), "R");
        bst.put("C".hashCode(), "C");
        bst.put("H".hashCode(), "H");
        bst.put("T".hashCode(), "T");
        bst.put("R".hashCode(), "R");
        bst.put("E".hashCode(), "E");
        bst.put("E".hashCode(), "E");

        Util.println(bst.get("S".hashCode()));
        Util.println("Size: " + bst.size());
        Util.println("Key=" + "R".hashCode() + ", Rank=" + bst.rank("R".hashCode()));

        bst.iterator().forEach(key -> Util.print(key + ","));
        Util.println();

        Util.println("Floor: " + bst.floor("E".hashCode()));
    }
}
