package com.bma.algorithms.minimum_spanning_trees;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class Edge implements Comparable<Edge> {

    private final int v, w;
    private final double weight;

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        return v;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}
