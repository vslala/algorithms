package com.bma.algorithms.graphs.directed_graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class DepthFirstOrderTest {

    private final Path graphFile = Path.of("src/test/resources/depth_first_order.txt");
    private DynamicDigraph graph;
    private DepthFirstOrder depthFirstOrder;

    @BeforeEach
    public void setup() throws IOException {
        graph = new DynamicDigraph(graphFile);
        depthFirstOrder = new DepthFirstOrder(graph);
    }

    @Test
    void itShouldReturnTheDepthFirstOrderOfTheGivenGraph() {
        Iterable<Integer> postOrder = depthFirstOrder.post();

    }

}