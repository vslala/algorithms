package com.bma.algorithms.graphs.directed_graphs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicDigraphTest {

    private static final Path graphPath = Path.of("src/test/resources/depth_first_order.txt");
    DynamicDigraph digraph = new DynamicDigraph(graphPath);

    DynamicDigraphTest() throws IOException {
    }

    @Test
    void itShouldGiveTotalVertices() {
        assertEquals(13, digraph.vertices());
    }

    @Test
    void itShouldGiveTotalEdgesInTheGraph() {
        assertEquals(22, digraph.edges());
    }
}