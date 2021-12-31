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

    /**
     * File content should be in the following format
     * Line 0: {int}:vertex
     * Line 1...n: {int, int, double}:{from, to, weight}
     * @param filePath
     * @return
     */
    static EdgeWeightedDigraph createDigraph(Path filePath) {
        return new EdgeWeightedDigraphImpl(filePath);
    }

    static EdgeWeightedDigraph createDigraph(int vertices) {
        return new EdgeWeightedDigraphImpl(vertices);
    }

    static EdgeWeightedDigraph createDigraph(In in) {
        return new ReferenceEdgeWeightedDigraphImpl(in);
    }

    /**
     *
     * @param input is of the format [[from, to, weight], [from, to, weight]]
     * @param numberOfVertices
     * @return
     */
    static EdgeWeightedDigraph createDigraph(int[][] input, int numberOfVertices) {
        EdgeWeightedDigraph digraph = EdgeWeightedDigraph.createDigraph(numberOfVertices);
        for (int[] edge : input) {
            digraph.addEdge(new DirectedEdge(edge[0], edge[1], edge[2]));
        }

        return digraph;
    }
}
