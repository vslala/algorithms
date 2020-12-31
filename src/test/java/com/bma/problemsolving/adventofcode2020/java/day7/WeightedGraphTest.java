package com.bma.problemsolving.adventofcode2020.java.day7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedGraphTest {
    private static final String PATH =  "/Users/in-varun.shrivastava/Code/src/github.com/java/Algorithms/src/main/java/com/bma/algorithms/adventofcode2020/inputs/day7test.txt";
    private WeightedGraph graph;

    @BeforeEach
    void  setup() {
        var graphBuilder = new WeightedGraphBuilder();
        graph = graphBuilder.buildFromFileInput(Path.of(PATH));
    }

    @Test
    void itShouldGiveAdjacentEdgesToTheGivenEdge() {
        List<WeightedEdge> adjEdges = graph.adj("light red");
        assertEquals(2, adjEdges.size());
    }

}