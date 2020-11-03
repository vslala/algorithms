package com.bma.algorithms.minimum_spanning_trees;

import com.bma.algorithms.graphs.EdgeWeightedGraph;
import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimsAlgorithmLazyTest {

    private EdgeWeightedGraph graph;
    private PrimsAlgorithmLazy prims;

    @BeforeEach
    void setup() {
        graph = new EdgeWeightedGraphImpl(10);
        graph.addEdge(new Edge(0, 2, 0.25));
        graph.addEdge(new Edge(2, 1, 0.15));
        graph.addEdge(new Edge(1, 3, 0.35));
        graph.addEdge(new Edge(3, 5, 0.55));
        graph.addEdge(new Edge(5, 7, 0.75));
        graph.addEdge(new Edge(7, 9, 0.45));

        prims = new PrimsAlgorithmLazy();
    }

    @Test
    void itShouldCreateMinimumSpanningTree() {
        graph.addEdge(new Edge(9, 0, 0.15));
        EdgeWeightedGraph mst = prims.mst(graph, 0);
        mst.edges().forEach(Util::println);
    }
}