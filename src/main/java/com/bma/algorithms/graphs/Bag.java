package com.bma.algorithms.graphs;

import java.util.HashSet;
import java.util.Set;

public class Bag<T> {
    Set<T> list;

    public Bag() {
        list = new HashSet<>();
    }

    public void add(T vertex) {
        list.add(vertex);
    }

    public Iterable<T> iterator() {
        return list;
    }
}
