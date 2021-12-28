package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Digraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StronglyConnectedComponentTest {
    private Digraph digraph;

    @BeforeEach
    void init() throws IOException {
        digraph = new AdjacencyListDigraphImpl(13, Path.of("src/test/resources/kosaraju-sharir-digraph-test.dat"));
    }

    @Test
    void findStronglyConnectedComponents() {
//        print();
        var scc = new StronglyConnectedComponents(digraph);
        int connectedComponents = scc.count();
        assertEquals(4, connectedComponents);
    }

    private void print() {
        IntStream.range(0, digraph.vertices())
                .forEach(vertex -> {
                    System.out.print(vertex + "->");
                    digraph.adj(vertex).forEach(w  -> {
                        System.out.print(w + " ");
                    });
                    System.out.println();
                });
    }

}