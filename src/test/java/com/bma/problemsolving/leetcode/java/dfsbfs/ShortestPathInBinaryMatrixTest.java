package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortestPathInBinaryMatrixTest {

    private ShortestPathInBinaryMatrix sol = new ShortestPathInBinaryMatrix();

    @ParameterizedTest
    @CsvSource(value = {
            "[[0,1],[1,0]] 2",
            "[[0,0,0],[1,1,0],[1,1,0]] 4",
            "[[1,0,0],[1,1,0],[1,1,0]] -1",
            "[[0,0,0],[1,0,0],[1,1,0]] 3",
            "[[0]] 1"
    }, delimiter = ' ')
    void shouldReturnTheShortestPathLengthInABinaryMatrix(String gridExpr, int expected) {
        int[][] grid = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(gridExpr, Integer.class));

        var result = sol.shortestPathBinaryMatrix(grid);

        assertEquals(expected, result);
    }
}