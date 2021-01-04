package com.bma.problemsolving.leetcode.java.lrucache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DoublyNode<K, V> {
    private DoublyNode<K, V> prev;
    private final K key;
    private final V data;
    private DoublyNode<K, V> next;
}
