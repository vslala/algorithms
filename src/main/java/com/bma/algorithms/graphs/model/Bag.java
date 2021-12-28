package com.bma.algorithms.graphs.model;

import java.util.ArrayList;
import java.util.List;

public class Bag<T> extends ArrayList<T> implements List<T> {

    public static <T> Bag<T> empty() {
        return new Bag<>();
    }

    public Iterable<T> itr() {
        return this;
    }
}
