package com.bma.problemsolving.meta.graph;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BipartiteGraphTest {

    private BipartiteGraph sol = new BipartiteGraph();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2,3],[0,2],[0,1,3],[0,2]] false",
            "[[1,3],[0,2],[1,3],[0,2]] true",
            "[[4,1],[0,2],[1,3],[2,4],[3,0]] false",
            "[[],[2,4,6],[1,4,8,9],[7,8],[1,2,8,9],[6,9],[1,5,7,8,9],[3,6,9],[2,3,4,6,9],[2,4,5,6,7,8]] false",
            "[[1],[0,3],[3],[1,2]] true"
    }, delimiter = ' ')
    void shouldTellWhetherAGraphIsABipartiteGraphOrNot(String graphExpr, boolean expected) {
        int[][] graph = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(graphExpr, Integer.class));

        boolean output = sol.isBipartite(graph);

        assertEquals(expected, output);
    }
}