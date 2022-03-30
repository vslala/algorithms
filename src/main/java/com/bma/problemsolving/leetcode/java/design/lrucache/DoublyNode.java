package com.bma.problemsolving.leetcode.java.design.lrucache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Key,Val is required in order to keep a back reference to the key
 * of the hashmap which is used in the LRUCacheSandbox.
 * @param <K>
 * @param <V>
 */
@Getter
@Setter
@RequiredArgsConstructor
public class DoublyNode<K, V> {
    private DoublyNode<K, V> prev;
    private final K key;
    private final V data;
    private DoublyNode<K, V> next;
}
