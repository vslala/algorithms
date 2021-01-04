package com.bma.problemsolving.leetcode.java.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * Basically combines the two implementation of
 * HashSet and DoublyLinkedList to achieve O(1) read and write to cache.
 *
 * There is a LinkedHashMap data structure in java that could be used to
 * implement this cache as well.
 * @param <K>
 * @param <V>
 */
class LRUCache<K, V> {

    private int size;
    private final int capacity;
    private final Map<K, DoublyNode<K, V>> lookup;
    private DoublyLinkedList<K, V> list;
    private V defaultValue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lookup = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public V get(K key) {
        /*
        Get the stored query result from the cache.

        Accessing a node updates its position to the front of the LRU list.
         */
        if (!lookup.containsKey(key))
            return defaultValue;

        final DoublyNode<K, V> node = lookup.get(key);
        list.moveToFront(node);
        return node.getData();
    }

    public void put(K key, V value) {
        /*
        Set the result for the given query key in the cache.

        When updating an entry, updates its position to the front of the LRU list.
        If the entry is new and the cache is at capacity, removes the oldest entry
        before the new entry is added.
         */
        if (lookup.containsKey(key)) {
            // Key exists in cache, update the value
            var node = lookup.get(key);
            list.moveToFront(node);
        } else {
            // Key does not exist in cache
            if (size == capacity) {
                // Remove the oldest entry from the linked list and lookup
                final DoublyNode<K, V> tail = list.end();
                lookup.remove(tail.getKey());
                list.removeFromTail();
            } else
                size++;

            // Add the new key and value
            final DoublyNode<K, V> node = new DoublyNode<>(key, value);
            list.appendToFront(node);
            lookup.put(key, node);
        }

    }

    public void defaultMissValue(V val) {
        this.defaultValue = val;
    }
}
