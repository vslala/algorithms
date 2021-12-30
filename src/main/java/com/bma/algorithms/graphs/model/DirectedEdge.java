package com.bma.algorithms.graphs.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
@Getter(AccessLevel.NONE)
public class DirectedEdge implements Comparable<DirectedEdge> {
    int v;
    int w;
    double weight;

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(DirectedEdge that) {
        return Double.compare(this.weight, that.weight);
    }
}
