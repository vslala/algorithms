package com.bma.algorithms.graphs.directed_graphs;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class PathFinderTest {

    @Test
    void givenASetOfVerticesFindThePathFromSourceToDestination() {
        var graph = Map.of(
                "A", List.of("B", "C"),
                "B", List.of("A", "D", "E"),
                "C", List.of("A", "F"),
                "D", List.of("B"),
                "E", List.of("B", "F"),
                "F", List.of("C", "E")
        );
        
        var test = new PathFinder(graph, "A");
        var result = test.findPath( "C");
        System.out.println("A  -> " + result);
        
    }

}