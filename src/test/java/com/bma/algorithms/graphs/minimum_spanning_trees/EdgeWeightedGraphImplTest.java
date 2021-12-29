package com.bma.algorithms.graphs.minimum_spanning_trees;

import com.bma.algorithms.graphs.model.Edge;
import com.bma.algorithms.priorityqueues.MinHeap;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void isShouldCreateGraphFromAFile() {
        var graph = new EdgeWeightedGraphImpl(Path.of("src/test/resources/graphs/minimum_spanning_trees/create_edge_weighted_graph.csv"));
        assertEquals(8, graph.totalVertices());
        assertEquals(15, graph.totalEdges());

        // verify if the correct edges are stored in the graph
        MinHeap<Edge> minHeap = new MinHeap<>();
        graph.edges().forEach(minHeap::add);
        assertEquals(new Edge(0, 7, 0.16), minHeap.remove());
        assertEquals(new Edge(2, 3, 0.17), minHeap.remove());
        assertEquals(new Edge(1, 7, 0.19), minHeap.remove());
        assertEquals(new Edge(0, 2, 0.26), minHeap.remove());
    }

}