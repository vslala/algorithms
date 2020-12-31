package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Digraph;
import com.bma.algorithms.stdlib.In;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class KosarajuSharirSCCTest {

    private final Path graphPath = Path.of("src/test/resources/depth_first_order.txt");
    private final DigraphImplRS graph = new DigraphImplRS(new In(graphPath.toFile()));
    private KosarajuSharirSCC kosarajuSharirSCC = new KosarajuSharirSCC(graph);

    KosarajuSharirSCCTest() throws IOException {

    }

    @Test
    public void itShouldTellIfTheGivenVertexAreStronglyConnected() {
        assertTrue(kosarajuSharirSCC.stronglyConnected(2, 5));
        assertFalse(kosarajuSharirSCC.stronglyConnected(0, 9));
    }

}