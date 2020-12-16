package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Graph;

public interface Digraph extends Graph {

    /**
     * Reverse of this diagraph
     * @return
     */
    Digraph reverse();
}
