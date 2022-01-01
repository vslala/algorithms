package com.bma.algorithms.graphs.shortest_paths;

import com.bma.algorithms.graphs.model.DirectedEdge;

public interface SingleSourceShortestPath {
    /**
     * Length of shortest path from 's' to 'v'
     * @param v target vertex
     * @return
     */
    double distTo(int v);

    /**
     * shortest path from 's' to 'v'
     * @param v
     * @return
     */
    Iterable<DirectedEdge> pathTo(int v);

    /**
     * Is there a path from 's' to 'v'
     * @param v
     * @return
     */
    boolean hasPathTo(int v);

    /**
     * return the total distance of the farthest node from the source
     * @return
     */
    double farthestReachableDistance();

    /**
     * Detect if the graph has any negative cycles
     * @return
     */
    boolean hasNegativeCycle();

    /**
     * Return the edges that creates the negative cycle
     * @return
     */
    Iterable<DirectedEdge> negativeCycle();

    /**
     * Common algorithm for relaxing edges used in Single Source Shortest Path algorithms
     * @param edge current edge to relax
     * @param distTo current distance to the incident vertex that will be updated if shorter distance found after relaxation
     * @param edgeTo edges to update after relaxation
     */
    default void relax(DirectedEdge edge, double[] distTo, DirectedEdge[] edgeTo) {
        int u = edge.from();
        int v = edge.to();
        double w = edge.weight();

        if (distTo[v] > distTo[u] + w) {
            distTo[v] = distTo[u] + w;
            edgeTo[v] = edge;
        }
    }
}
