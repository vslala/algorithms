package com.bma.algorithms.graphs.shortest_paths;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;
import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DijkstraAlgorithmTest {

    @Test
    void shouldReturnTheShortestPathFromSourceToDestination() {
        // Given
        EdgeWeightedDigraph edgeWeightedDigraph = EdgeWeightedDigraph.createDigraph(Path.of("src/test/resources/graphs/shortestpath/directed_graph.csv"));

        // When
        DijkstraAlgorithm sol = new DijkstraAlgorithm(edgeWeightedDigraph, 0);

        // Then
        // distance to the given node should be the minimum
        assertEquals(25.0, sol.distTo(6));

        // should have a path to all the connecting edges
        assertTrue(sol.hasPathTo(1));
        assertTrue(sol.hasPathTo(2));
        assertTrue(sol.hasPathTo(3));
        assertTrue(sol.hasPathTo(4));
        assertTrue(sol.hasPathTo(5));
        assertTrue(sol.hasPathTo(6));
        assertTrue(sol.hasPathTo(7));

        // Then
        Iterable<DirectedEdge> edges = sol.pathTo(6);
        for (DirectedEdge e: edges) Util.println(e);

        // assert that the path is indeed the shortest path
        Iterator<DirectedEdge> itr = edges.iterator();
        assertEquals(new DirectedEdge(2, 6, 11.0), itr.next());
        assertEquals(new DirectedEdge(5, 2, 1.0), itr.next());
        assertEquals(new DirectedEdge(4, 5, 4.0), itr.next());
        assertEquals(new DirectedEdge(0, 4, 9.0), itr.next());
    }

}