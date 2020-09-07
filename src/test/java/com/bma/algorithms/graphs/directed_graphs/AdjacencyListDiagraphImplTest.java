package com.bma.algorithms.graphs.directed_graphs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyListDiagraphImplTest {

    private Diagraph diagraph;

    @Before
    public void setup() {
        diagraph = new AdjacencyListDiagraphImpl(13);
    }

    @Test
    public void itShouldPrintAllTheDirectedEdgesInTheGraph() {
        diagraph.addEdge(0, 1);
        diagraph.addEdge(0, 5);
        diagraph.addEdge(2, 0);
        diagraph.addEdge(2, 3);
        diagraph.addEdge(3, 2);
        diagraph.addEdge(3, 5);
        diagraph.addEdge(4, 3);
        diagraph.addEdge(4, 2);
        diagraph.addEdge(5, 4);
        diagraph.addEdge(6, 8);
        diagraph.addEdge(6, 9);
        diagraph.addEdge(6, 4);
        diagraph.addEdge(7, 9);
        diagraph.addEdge(7, 6);
        diagraph.addEdge(8, 6);
        diagraph.addEdge(9, 10);
        diagraph.addEdge(9, 11);
        diagraph.addEdge(10, 12);
        diagraph.addEdge(10, 12);
        diagraph.addEdge(11, 12);
        diagraph.addEdge(11, 4);
        diagraph.addEdge(12, 9);

        System.out.println(diagraph.toString());
    }

}