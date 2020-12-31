package com.bma.algorithms.graphs.undirected_graphs;

import com.bma.algorithms.graphs.Digraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BreadthFirstPathsTest {

    Digraph graph;

    @BeforeEach
    public void setup() {
        graph = new AdjacencyListGraphImpl(10);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
    }

    @Test
    public void itShouldSearchInGraphUsingBreadthFirstTraversal() {
        var bfs = new BreadthFirstPaths(graph, 0);
        bfs.pathTo(8);
        assertTrue(bfs.hasPathTo(8));
        System.out.println(bfs);
    }

}