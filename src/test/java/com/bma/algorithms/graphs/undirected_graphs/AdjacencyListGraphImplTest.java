package com.bma.algorithms.graphs.undirected_graphs;

import org.junit.Test;

import static com.bma.algorithms.graphs.undirected_graphs.GraphProcessor.*;
import static org.junit.Assert.*;

public class AdjacencyListGraphImplTest {

    @Test
    public void itShouldTestTheGraphApiImplementation() {
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
        assertEquals(1, numberOfSelfLoops(graph));
        assertEquals(2, degree(graph, 4));
        assertEquals(6, maxDegree(graph));

        System.out.println(graph.toString());
    }

}