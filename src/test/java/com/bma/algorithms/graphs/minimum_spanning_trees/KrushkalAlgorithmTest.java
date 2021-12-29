package com.bma.algorithms.graphs.minimum_spanning_trees;

import com.bma.algorithms.graphs.model.Edge;
import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KrushkalAlgorithmTest {

    private EdgeWeightedGraphImpl graph;

    @BeforeEach
    void setup() {
        graph = new EdgeWeightedGraphImpl(Path.of("src/main/resources/algorithms/graphs/minimum_spanning_trees/test_graph.csv"));
    }

    @Test
    void itShouldCreateMinSpanningTree() {
        var kruskal = new KrushkalAlgorithm(graph);

        EdgeWeightedGraph graph = kruskal.spanningTree();
        graph.edges().forEach(edge -> Util.println(edge + ","));
        int v0 = 0;
        int v1 = 1;
        int v2 = 2;
        int v3 = 3;
        int v4 = 4;
        int v5 = 5;
        int v6 = 6;
        int v7 = 7;

        // property of spanning tree is that it contains edges = totalVertices - 1;
        assertEquals(8, graph.totalVertices());
        assertEquals(v7, graph.totalEdges());

        // check for every edge that should be included in the minimum spanning tree
        // from vertex 0
        Iterator<Edge> adjEdgesFromVertex0 = graph.adj(v0).iterator();
        assertEquals(new Edge(v0, v7, 0.16), adjEdgesFromVertex0.next());
        assertEquals(new Edge(v0, v2, 0.26), adjEdgesFromVertex0.next());

        // from vertex 1
        assertEquals(new Edge(v1, v7, 0.19), graph.adj(v1).iterator().next());

        // from vertex 2
        assertEquals(new Edge(v2, v3, 0.17), graph.adj(v2).iterator().next());

        // no outward edge from vertex 3
        // from vertex 4
        assertEquals(new Edge(v4, v5, 0.35), graph.adj(v4).iterator().next());

        // from vertex 5
        assertEquals(new Edge(v5, v7, 0.28), graph.adj(v5).iterator().next());

        // from vertex 6
        assertEquals(new Edge(v6, v2, 0.4), graph.adj(v6).iterator().next());
    }

    @Test
    void itShouldIgnoreEdgeThatCreatesCycle() {
        graph.addEdge(new Edge(9, 0, 0.11));
        var kruskal = new KrushkalAlgorithm(graph);
        EdgeWeightedGraph graph = kruskal.spanningTree();

        graph.edges().forEach(edge -> Util.print(edge + ","));

        assertEquals(7, graph.totalEdges());
    }

}