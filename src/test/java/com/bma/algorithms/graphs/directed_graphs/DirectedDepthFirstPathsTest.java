package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.undirected_graphs.AdjacencyListGraphImpl;
import com.bma.algorithms.graphs.undirected_graphs.DepthFirstPaths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectedDepthFirstPathsTest {
    AdjacencyListGraphImpl graph = new AdjacencyListGraphImpl(10);

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
        var dfs = new DirectedDepthFirstPaths(graph, 0);
        Iterable<Integer> path = dfs.pathTo(5);
        assertTrue(dfs.hasPathTo(5));
        System.out.println(StreamSupport.stream(path.spliterator(), false)
                .map(String::valueOf).collect(Collectors.joining("->")));
    }
}