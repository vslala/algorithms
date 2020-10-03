package com.bma.algorithms.graphs.undirected_graphs;

import com.bma.algorithms.graphs.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CCTest {

    Graph graph;

    @BeforeEach
    public void setup() {
        graph = new AdjacencyListGraphImpl(13);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 6);
        graph.addEdge(0, 5);
        graph.addEdge(6, 4);
        graph.addEdge(4, 3);
        graph.addEdge(3, 5);
        graph.addEdge(7, 8);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(9, 12);
        graph.addEdge(11, 12);
    }

    @Test
    public void itShouldTellWhetherTwoEdgesAreConnectedOrNot() {
        var connectedComponents = new CC(graph);
        assertTrue(connectedComponents.connected(0, 5));
        assertFalse(connectedComponents.connected(0, 7));
    }

    @Test
    public void itShouldGiveTotalConnectedComponentsCount() {
        var connectedComponents = new CC(graph);
        assertEquals(3, connectedComponents.count());
    }

    @Test
    public void itShouldGiveTheIdOfThePassedVertex() {
        var connectedComponents = new CC(graph);
        assertEquals(0, connectedComponents.id(0));
        assertEquals(1, connectedComponents.id(7));
        assertEquals(2, connectedComponents.id(9));
    }



}