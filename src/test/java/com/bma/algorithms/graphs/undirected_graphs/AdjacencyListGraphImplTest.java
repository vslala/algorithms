package com.bma.algorithms.graphs.undirected_graphs;

import org.junit.jupiter.api.Test;

import static com.bma.algorithms.graphs.undirected_graphs.GraphProcessor.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjacencyListGraphImplTest {

    @Test
    void itShouldTestTheGraphApiImplementation() {
        var graph = new AdjacencyListGraphImpl(10);
        graph.addEdge(0, 5);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(4, 0);
        graph.addEdge(5, 4);
        graph.addEdge(0, 0);

        assertEquals(10, graph.vertices());
        assertEquals(2, numberOfSelfLoops(graph));  // in an undirected graph every edge is counted twice
        assertEquals(3, degree(graph, 4));  // in an undirected graph every edge is counted twice
        assertEquals(8, maxDegree(graph)); // '0' has a self loop as well

        System.out.println(graph.toString());
    }

}