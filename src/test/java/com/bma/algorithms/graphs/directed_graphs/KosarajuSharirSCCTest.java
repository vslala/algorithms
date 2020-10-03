package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class KosarajuSharirSCCTest {

    private final Path graphPath = Path.of("src/test/resources/depth_first_order.txt");
    private final Graph graph = new DynamicDigraph(graphPath);
    private KosarajuSharirSCC kosarajuSharirSCC = new KosarajuSharirSCC(graph);

    KosarajuSharirSCCTest() throws IOException {

    }

    @Test
    public void itShouldTellIfTheGivenVertexAreStronglyConnected() {
        assertTrue(kosarajuSharirSCC.stronglyConnected(2, 5));
        assertFalse(kosarajuSharirSCC.stronglyConnected(0, 9));
    }

}