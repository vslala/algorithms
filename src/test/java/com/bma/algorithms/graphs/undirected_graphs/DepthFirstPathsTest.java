package com.bma.algorithms.graphs.undirected_graphs;

import com.bma.algorithms.graphs.Digraph;
import com.bma.algorithms.graphs.directed_graphs.AdjacencyListDigraphImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepthFirstPathsTest {

    Digraph graph = new AdjacencyListDigraphImpl(10);

    @BeforeEach
    public void setup() {
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
    }


    @Test
    public void itShouldPerformADepthFirstSearchToFindThePathFromSourceVertexToDestination() {
        var dfs = new DepthFirstPaths(graph, 0);
        dfs.pathTo(5);
        assertTrue(dfs.hasPathTo(5));
    }

}