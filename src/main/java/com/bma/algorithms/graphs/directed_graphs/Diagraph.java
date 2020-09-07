package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Graph;

public interface Diagraph extends Graph {

    /**
     * Reverse of this diagraph
     * @return
     */
    Diagraph reverse();
}
