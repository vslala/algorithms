package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllPathsFromSourceLeadToDestinationTest {

    private AllPathsFromSourceLeadToDestination sol = new AllPathsFromSourceLeadToDestination();

    @ParameterizedTest
    @CsvSource(value = {
            "3 [[0,1],[0,2]] 0 2 false",
            "4 [[0,1],[0,3],[1,2],[2,1]] 0 3 false",
            "4 [[0,1],[0,2],[1,3],[2,3]] 0 3 true",
            "2 [[0,1],[1,1]] 0 1 false",
            "2 [[1,0]] 0 1 false",
            "5 [[0,1],[0,2],[0,3],[0,3],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]] 0 4 true"
    }, delimiter = ' ')
    void shouldReturnTrueIffAllPathsFromSourceLeadToDestination(int n, String arrExpr, int source, int destination, boolean expected) {
        int[][] edges = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(arrExpr, Integer.class));

        var result = sol.leadsToDestination(n, edges, source, destination);

        assertEquals(expected, result);
    }
}