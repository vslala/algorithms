package com.bma.algorithms.graphs.directed_graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdjacencyListDigraphImplTest {

    private Digraph digraph;

    @BeforeEach
    public void setup() {
        digraph = new AdjacencyListDigraphImpl(13);
    }

    @Test
    public void itShouldPrintAllTheDirectedEdgesInTheGraph() {
        digraph.addEdge(0, 1);
        digraph.addEdge(0, 5);
        digraph.addEdge(2, 0);
        digraph.addEdge(2, 3);
        digraph.addEdge(3, 2);
        digraph.addEdge(3, 5);
        digraph.addEdge(4, 3);
        digraph.addEdge(4, 2);
        digraph.addEdge(5, 4);
        digraph.addEdge(6, 8);
        digraph.addEdge(6, 9);
        digraph.addEdge(6, 4);
        digraph.addEdge(7, 9);
        digraph.addEdge(7, 6);
        digraph.addEdge(8, 6);
        digraph.addEdge(9, 10);
        digraph.addEdge(9, 11);
        digraph.addEdge(10, 12);
        digraph.addEdge(10, 12);
        digraph.addEdge(11, 12);
        digraph.addEdge(11, 4);
        digraph.addEdge(12, 9);

        System.out.println(digraph.toString());
    }

}