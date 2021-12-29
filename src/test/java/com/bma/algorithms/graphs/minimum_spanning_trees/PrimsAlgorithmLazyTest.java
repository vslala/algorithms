package com.bma.algorithms.graphs.minimum_spanning_trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimsAlgorithmLazyTest {

    private EdgeWeightedGraph graph;
    private PrimsAlgorithmLazy prims;

    @BeforeEach
    void setup() {
        graph = new EdgeWeightedGraphImpl(Path.of("src/main/resources/algorithms/graphs/minimum_spanning_trees/test_graph.csv"));
        prims = new PrimsAlgorithmLazy(graph);
    }

    @Test
    void itShouldCreateMinimumSpanningTree() {
        double minWeight = prims.minWeightOfSpanningTree();
        assertEquals(1.81, minWeight);
    }
}