package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.model.DirectedEdge;
import com.bma.algorithms.stdlib.In;

import java.nio.file.Path;

public interface EdgeWeightedDigraph {
    int totalVertices();

    int totalEdges();

    void addEdge(DirectedEdge e);

    Iterable<DirectedEdge> adj(int v);

    int outDegree(int v);

    int inDegree(int v);

    Iterable<DirectedEdge> edges();

    static EdgeWeightedDigraph createDigraph(Path filePath) {
        return new EdgeWeightedDigraphImpl(filePath);
    }

    static EdgeWeightedDigraph createDigraph(int vertices) {
        return new EdgeWeightedDigraphImpl(vertices);
    }

    static EdgeWeightedDigraph createDigraph(In in) {
        return new ReferenceEdgeWeightedDigraphImpl(in);
    }
}
