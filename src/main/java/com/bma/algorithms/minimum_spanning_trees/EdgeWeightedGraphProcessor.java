package com.bma.algorithms.minimum_spanning_trees;

import com.bma.algorithms.graphs.EdgeWeightedGraph;
import com.bma.algorithms.sort.elementary.Util;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class EdgeWeightedGraphProcessor<T extends EdgeWeightedGraph> {

    private boolean[] marked;
    private AtomicBoolean cycleExists = new AtomicBoolean(false);

    public boolean detectCycle(EdgeWeightedGraph graph, int vertex, int totalVertices) {
        if (Objects.isNull(marked)) marked = new boolean[totalVertices];
        marked[vertex] = true;
        graph.adj(vertex).forEach(edge -> {
            int w = edge.other(vertex);
            Util.println(":  " + w);
            if (!marked[w])
                detectCycle(graph, w, totalVertices);
            else
                cycleExists.set(true); // cycle exists
        });

        return cycleExists.get();
    }
}
