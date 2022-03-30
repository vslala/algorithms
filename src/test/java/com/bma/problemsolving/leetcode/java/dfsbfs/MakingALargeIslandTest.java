package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MakingALargeIslandTest {

    private MakingALargeIsland sol = new MakingALargeIsland();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,0],[0,1]] 3",
            "[[1,1],[1,0]] 4",
            "[[1,1],[1,1]] 4",
            "[[0,0,0,0,0,0,0],[0,1,1,1,1,0,0],[0,1,0,0,1,0,0],[1,0,1,0,1,0,0],[0,1,0,0,1,0,0],[0,1,0,0,1,0,0],[0,1,1,1,1,0,0]] 18",
            "[[0,0],[0,1]] 2",
    }, delimiter = ' ')
    void shouldReturnTheMaxSizeOfIslandByChangeAnyOneZeroToOne(String expr, int expected) {
        int[][] grid = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expr, Integer.class));

        int output = sol.largestIsland(grid);

        assertEquals(expected, output);
    }

}