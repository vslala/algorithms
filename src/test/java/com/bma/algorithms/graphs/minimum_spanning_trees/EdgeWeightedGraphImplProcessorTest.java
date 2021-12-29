package com.bma.algorithms.graphs.minimum_spanning_trees;

import com.bma.algorithms.graphs.model.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EdgeWeightedGraphImplProcessorTest {

    private EdgeWeightedGraphProcessor<EdgeWeightedGraph> gp;
    private EdgeWeightedGraph graph;

    @BeforeEach
    void setup() {
        graph = new EdgeWeightedGraphImpl(10);
        graph.addEdge(new Edge(0, 2, 0.25));
        graph.addEdge(new Edge(1, 3, 0.35));
        graph.addEdge(new Edge(3, 5, 0.55));
        graph.addEdge(new Edge(5, 7, 0.75));
        graph.addEdge(new Edge(7, 9, 0.45));
        gp = new EdgeWeightedGraphProcessor<>();
    }

    @Test
    void itShouldCheckNegativeForACycleInTheGraph() {
        boolean flag = gp.detectCycle(graph, 0, graph.totalVertices());
        assertFalse(flag);
    }

    @Test
    void itShouldCheckForAnExistingCycleInTheGraph() {
        graph.addEdge(new Edge(0,  5, 0.44));
        graph.addEdge(new Edge(5,  0, 0.44));
        graph.addEdge(new Edge(1,  5, 0.44));
        graph.addEdge(new Edge(9,  5, 0.44));
        boolean flag = gp.detectCycle(graph, 0, graph.totalVertices());
        assertTrue(flag);
    }

    @Test
    void sandbox() {
        var graph = new EdgeWeightedGraphImpl(10);
        graph.addEdge(new Edge(0, 2, 0.25));
        graph.addEdge(new Edge(2, 1, 0.15));
        graph.addEdge(new Edge(1, 3, 0.35));
        graph.addEdge(new Edge(3, 5, 0.55));
        graph.addEdge(new Edge(5, 7, 0.75));
        graph.addEdge(new Edge(7, 9, 0.45));
        graph.addEdge(new Edge(9, 0, 0.45));
        boolean flag = gp.detectCycle(graph, 0, graph.totalVertices());
        System.out.println(flag);
        assertTrue(flag);
    }

}