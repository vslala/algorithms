package com.bma.algorithms.graphs;

import com.bma.algorithms.minimum_spanning_trees.Edge;

public interface EdgeWeightedGraph {
    void addEdge(Edge edge);

    int totalEdges();

    Iterable<Edge> adj(int v);

    Iterable<Edge> edges();

    int totalVertices();

    boolean removeEdge(Edge edge);
}
