package com.bma.problemsolving.leetcode.java.design.lrucache;

import java.util.LinkedHashMap;

public class LRUCacheWithLinkedHashMap {

    private int capacity;
    private int size;
    private LinkedHashMap<Integer, Integer> lookup;

    public LRUCacheWithLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lookup = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public Integer get(Integer key) {
        if (!lookup.containsKey(key))
            return  -1;

        return lookup.get(key);
    }

    public void put(Integer key, Integer value) {
        if (lookup.containsKey(key)) {
            lookup.remove(key);
            lookup.put(key, value);
            return;
        }

        if (size == capacity) {
            var item = lookup.entrySet().iterator().next();
            lookup.remove(item.getKey());
        } else
            size++;

        lookup.put(key, value);
    }
}
