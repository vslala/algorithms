package com.bma.algorithms.tries;

/*
    - Neither keys nor characters are explicitly stored
    - Characters are implicitly defined by link index
    - Each node has an array of links and a value

 */

import java.util.Objects;

public class TrieST<Value> {
    private static final int R = 256;   // extended ASCII
    private TrieNode root = new TrieNode();

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private TrieNode put(TrieNode x, String key, Value value, int d) {
        if (x == null) x = new TrieNode();

        if (d == key.length()) {
            x.value = value;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d + 1);
        return x;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        TrieNode x = get(root, key, 0);
        if (Objects.isNull(x)) return null;
        return (Value) x.value;
    }

    private TrieNode get(TrieNode x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);

        return get(x.next[c], key, d + 1);
    }

    public void delete(String key) {
        delete(root, key, 0);
    }

    private void delete(TrieNode x, String key, int d) {
        if (x == null) return;
        if (d == key.length()) {
            x.value = null;
            deleteLink(x);
            return;
        }
        char c = key.charAt(d);
        delete(x.next[c], key, d+1);
    }

    private void deleteLink(TrieNode x) {
        int nullCount = 0;
        for (TrieNode i : x.next)
            if (i == null) nullCount++;

        if (nullCount == x.next.length)
            x.next = null;
    }
}
