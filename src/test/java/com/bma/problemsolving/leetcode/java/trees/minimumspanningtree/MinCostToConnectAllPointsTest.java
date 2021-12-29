package com.bma.problemsolving.leetcode.java.trees.minimumspanningtree;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinCostToConnectAllPointsTest {

    private MinCostToConnectAllPoints sol = new MinCostToConnectAllPoints();

    @ParameterizedTest
    @CsvSource(value = {
            "[[0,0],[2,2],[3,10],[5,2],[7,0]] 20",
            "[[3,12],[-2,5],[-4,1]] 18"
    }, delimiter = ' ')
    void shouldReturnTheMinimumCostOfConnectingAllCoordinatesOfA2DPlane(String inputExpr, int expected) {
        int[][] points = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class));

        int result = sol.minCostConnectPoints(points);

        assertEquals(expected, result);
    }
}