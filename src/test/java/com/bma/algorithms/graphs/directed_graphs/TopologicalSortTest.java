package com.bma.algorithms.graphs.directed_graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortTest {

    private Digraph digraph;

    @BeforeEach
    void init() {
        var edges = List.of(
                List.of(0, 1),
                List.of(0, 5),
                List.of(0, 2),
                List.of(1, 4),
                List.of(5, 2),
                List.of(3, 2),
                List.of(3, 5),
                List.of(3, 4),
                List.of(3, 6),
                List.of(6, 0),
                List.of(6, 4)
        );

        digraph = new AdjacencyListDigraphImpl(10);
        edges.forEach(edge -> digraph.addEdge(edge.get(0), edge.get(1)));
    }

    @Test
    void itShouldSortTheGivenGraphTopologically() {
        var topologicalSort = new TopologicalSort(digraph);
        List<Integer> sorted = topologicalSort.sort();
        sorted.forEach(vertex -> System.out.print(vertex +  "->"));
    }

}