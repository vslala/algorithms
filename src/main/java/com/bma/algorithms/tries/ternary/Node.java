package com.bma.algorithms.tries.ternary;

import lombok.Data;

@Data
public class Node<V> {
    V val;
    char c;
    Node<V> left;
    Node<V> mid;
    Node<V> right;
}
