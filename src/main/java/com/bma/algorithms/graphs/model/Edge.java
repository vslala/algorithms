package com.bma.algorithms.graphs.model;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@ToString
@RequiredArgsConstructor
@Value
public class Edge implements Comparable<Edge> {

    int v, w;
    double weight;

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        return v;
    }

    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}
