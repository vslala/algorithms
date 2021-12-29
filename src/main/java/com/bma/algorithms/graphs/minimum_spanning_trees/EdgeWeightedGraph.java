package com.bma.algorithms.graphs.minimum_spanning_trees;

import com.bma.algorithms.graphs.model.Edge;

public interface EdgeWeightedGraph {
    void addEdge(Edge edge);

    int totalEdges();

    Iterable<Edge> adj(int v);

    Iterable<Edge> edges();

    int totalVertices();

    boolean removeEdge(Edge edge);

    static EdgeWeightedGraph createUnDirectedGraph(int totalVertices) {
        return new EdgeWeightedGraphImpl(totalVertices);
    }
}
