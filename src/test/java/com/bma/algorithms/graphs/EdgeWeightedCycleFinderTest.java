package com.bma.algorithms.graphs;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EdgeWeightedCycleFinderTest {

    @Test
    void shouldDetectACycleAndReturnTheEdgesFormingTheCycle() {
        // Given
        String graphExpr = "[[0,5,0],[5,4,0],[4,3,0],[3,5,0]]";
        int numOfVertices = 6;
        List<List<Integer>> parsedExpression = Fixtures.parseNestedArrExpression(graphExpr, Integer.class);
        int[][] adjList = Fixtures.convertToPrimitiveArrMatrix(parsedExpression);
        EdgeWeightedDigraph digraph = EdgeWeightedDigraph.createDigraph(adjList, numOfVertices);

        // When
        EdgeWeightedCycleFinder cycleFinder = new EdgeWeightedCycleFinder(digraph);

        // Then
        assertTrue(cycleFinder.hasCycle());
        assertFalse(cycleFinder.hasNegativeCycle());

        // Then: verify that the cycle contains the correct edges
        Iterable<DirectedEdge> cycle = cycleFinder.cycle();
        Iterator<DirectedEdge> itr = cycle.iterator();

        assertEquals(new DirectedEdge(3, 5, 0), itr.next());
        assertEquals(new DirectedEdge(5, 4, 0), itr.next());
        assertEquals(new DirectedEdge(4, 3, 0), itr.next());
    }

    @Test
    void shouldDetectANegativeCycleAndReturnTheEdgesFormingTheCycle() {
        // Given
        String graphExpr = "[[0,5,-1],[5,4,-1],[4,3,-1],[3,5,-1]]";
        int numOfVertices = 6;
        List<List<Integer>> parsedExpression = Fixtures.parseNestedArrExpression(graphExpr, Integer.class);
        int[][] adjList = Fixtures.convertToPrimitiveArrMatrix(parsedExpression);
        EdgeWeightedDigraph digraph = EdgeWeightedDigraph.createDigraph(adjList, numOfVertices);

        // When
        EdgeWeightedCycleFinder cycleFinder = new EdgeWeightedCycleFinder(digraph);

        // Then
        assertTrue(cycleFinder.hasNegativeCycle());

        // Then: verify that the cycle contains the correct edges
        Iterable<DirectedEdge> cycle = cycleFinder.cycle();
        Iterator<DirectedEdge> itr = cycle.iterator();

        assertEquals(new DirectedEdge(3, 5, -1), itr.next());
        assertEquals(new DirectedEdge(5, 4, -1), itr.next());
        assertEquals(new DirectedEdge(4, 3, -1), itr.next());
    }
}