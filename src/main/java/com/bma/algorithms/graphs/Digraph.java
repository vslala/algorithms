package com.bma.algorithms.graphs;

public interface Digraph {

    /**
     * draws an edge connecting two vertex v-w
     * @param fromVertex
     * @param toVertex
     */
    void addEdge(int fromVertex, int toVertex);

    /**
     * vertices adjacent to vertex
     * @param vertex
     * @return
     */
    Iterable<Integer> adj(int vertex);

    /**
     * returns number of vertices in the graph
     * @return
     */
    int vertices();

    /**
     * returns number of edges in the graph
     * @return
     */
    int edges();

    /**
     * Reverse of this diagraph
     * @return
     */
    Digraph reverse();

    /**
     * string representation of the graph
     * @return
     */
    String toString();

}
