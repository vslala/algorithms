package com.bma.problemsolving.leetcode.java.disjointsets;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphValidTreeTest {
    private GraphValidTree gvt = new GraphValidTree();

    @ParameterizedTest
    @CsvSource({
            "5, [[0_1]:[0_2]:[0_3]:[1_4]], true",
            "5, [[0_1]:[1_2]:[2_3]:[1_3],[1_4]], false",
            "5, [[0_1]:[0_4]:[1_4]:[2_3]], false"
    })
    void shouldReturnTrueIfTheGivenGraphIsAValidTree(int vertices, String expression, boolean expected) {
        int[][] edges = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(expression));

        var result = gvt.validTree(vertices, edges);

        assertEquals(expected, result);
    }
}