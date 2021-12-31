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
}
