package com.bma.algorithms.graphs.model;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@ToString
@RequiredArgsConstructor
@Value
public class Edge implements Comparable<Edge> {

    int u, v;
    double weight;

    public int either() {
        return u;
    }

    public int other(int vertex) {
        if (vertex == u) return v;
        return u;
    }

    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}
