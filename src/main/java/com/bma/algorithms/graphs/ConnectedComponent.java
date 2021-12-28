package com.bma.algorithms.graphs;

public interface ConnectedComponent {

    /**
     * Check whether there is an edge between the two vertices
     * @param sourceVertex
     * @param destVertex
     * @return
     */
    boolean connected(int sourceVertex, int destVertex);

    /**
     * number of connected components
     * @return
     */
    int count();

    /**
     * Component identifier for the given Vertex
     * @param vertex
     * @return
     */
    int id(int vertex);
}
