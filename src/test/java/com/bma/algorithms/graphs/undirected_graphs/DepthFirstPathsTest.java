package com.bma.algorithms.graphs.undirected_graphs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepthFirstPathsTest {

    AdjacencyListGraphImpl graph = new AdjacencyListGraphImpl(10);

    @Before
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
        System.out.println(dfs.toString());
    }

}