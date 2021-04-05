package com.bma.algorithms.tries.ternary;

import com.bma.algorithms.tries.StringST;

import java.util.Objects;

public class TrieTST<Value> implements StringST<Value> {
    private Node<Value> root;

    public TrieTST() {
    }


    @Override
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>();
            x.c = c;
        }

        if (c < x.c)
            x.left = put(x.left, key, val, d);

        else if (c > x.c)
            x.right = put(x.right, key, val, d);

        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, val, d + 1);

        else
            x.val = val;

        return x;
    }

    @Override
    public boolean contains(String key) {
        Node<Value> x = get(root, key, 0);
        return !Objects.isNull(x);
    }

    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null) return null;

        char c = key.charAt(d);

        if      (c < x.c)               return get(x.left, key, d);
        else if (c > x.c)               return get(x.right, key, d);
        else if (d < key.length() - 1)  return get(x.mid, key, d + 1);
        else return x;
    }

    @Override
    public Value get(String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public Iterable<String> keys() {
        return null;
    }

    @Override
    public Iterable<String> keysWithPrefix(String s) {
        return null;
    }

    @Override
    public Iterable<String> keysThatMatch(String s) {
        return null;
    }

    @Override
    public String longestPrefixOf(String s) {
        return null;
    }
}
