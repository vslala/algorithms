package com.bma.algorithms.hash_tables;

public class LinearProbingHashST<Key, Value> {

    private int length      = 3001;
    private Key[] keys      = (Key[]) new Object[length];
    private Value[] values  = (Value[]) new Object[length];
    private int size        = 0;    // maintain the number of values inserted into the hashset.

    public void put(Key key, Value val) {
        // compute the next empty index
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % length) {
            if (keys[i].equals(key)) {
                size--;
                break;
            }
        }

        keys[i] = key;
        values[i] = val;
        size++;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % length;
    }

    public int size() {
        return size;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % length) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }
}
