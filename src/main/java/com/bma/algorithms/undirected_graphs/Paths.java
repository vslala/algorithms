package com.bma.algorithms.undirected_graphs;

public interface Paths {

    /**
     * returns a boolean stating if there is a path
     * from source (usually passed during initialization in constructor)
     * to the destination @param vertex
     * @param vertex
     * @return
     */
    boolean hasPathTo(int vertex);

    /**
     * returns all the visited vertices
     * from source (usually passed during initialization in constructor)
     * to the destination @param vertex
     * @param vertex
     * @return
     */
    Iterable<Integer> pathTo(int vertex);
}
