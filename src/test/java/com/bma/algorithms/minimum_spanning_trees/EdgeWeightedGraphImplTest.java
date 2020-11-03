package com.bma.algorithms.minimum_spanning_trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeWeightedGraphImplTest {

    @Test
    void itShouldAddEdgeToTheGraph() {
        var graph = new EdgeWeightedGraphImpl(10);
        graph.addEdge(new Edge(0, 2, 0.25));
        graph.addEdge(new Edge(1, 3, 0.35));
        graph.addEdge(new Edge(3, 5, 0.55));
        graph.addEdge(new Edge(5, 7, 0.75));
        graph.addEdge(new Edge(7, 9, 0.45));

        assertEquals(5, graph.totalEdges());
        assertEquals(3, graph.adj(1).iterator().next().other(1));
    }

}