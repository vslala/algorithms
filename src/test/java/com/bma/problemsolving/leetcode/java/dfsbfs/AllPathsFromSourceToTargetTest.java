package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AllPathsFromSourceToTargetTest {

    private AllPathsFromSourceToTarget sol = new AllPathsFromSourceToTarget();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2],[3],[3],[]] [[0,1,3],[0,2,3]]",
            "[[4,3,1],[3,2,4],[3],[4],[]] [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]"
    }, delimiter = ' ')
    void shouldReturnAllPathsFromSourceToTarget(String graphExpr, String expectedExpr) {
        int[][] graph = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(graphExpr, Integer.class));
        int[][] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class));

        var result = sol.allPathsSourceTarget(graph);

        Fixtures.assertBothMatrixContainsSameItems(graph, expected, Fixtures.convertToPrimitiveArrMatrix(result));
    }
}