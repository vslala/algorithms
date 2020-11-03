package com.bma.algorithms.shortest_paths;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class DirectedEdge {

    private final int v;
    private final int w;
    private final double weight;

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

}
