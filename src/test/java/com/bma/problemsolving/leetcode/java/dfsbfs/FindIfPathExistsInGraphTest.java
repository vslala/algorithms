package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindIfPathExistsInGraphTest {

    private FindIfPathExistsInGraph sol = new FindIfPathExistsInGraph();

    @ParameterizedTest
    @CsvSource(value = {
            "3 [[0,1],[1,2],[2,0]] 0 2 true",
            "6 [[0,1],[0,2],[3,5],[5,4],[4,3]] 0 5 false",
            "1 [] 0 0 true"
    }, delimiter = ' ')
    void shouldReturnTrueIfPathExistsBetweenTwoNodes(int vertices, String edgesExpr, int start, int end, boolean expected) {
        int[][] edges = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(edgesExpr, Integer.class));

        boolean result = sol.validPath(vertices, edges, start, end);

        assertEquals(expected, result);
    }

}