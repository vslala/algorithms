package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Graph;
import com.bma.algorithms.stdlib.StdOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;
import java.util.Queue;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

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
        Deque<Integer> queue = depthFirstOrder.postOrder();
        StdOut.println(queue.stream().map(String::valueOf).collect(joining(",")));
    }

}