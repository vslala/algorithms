package com.bma.algorithms.graphs;

import java.util.ArrayList;

public class Bag<T> extends ArrayList<T> {

    public Iterable<T> itr() {
        return this;
    }
}
