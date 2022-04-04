package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Comparator;

class KClosestPointsToOriginTest {

    private KClosestPointsToOrigin sol = new KClosestPointsToOrigin();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,3],[-2,2]] 1 [[-2,2]]",
            "[[3,3],[5,-1],[-2,4]] 2 [[3,3],[-2,4]]",
            "[[2,2],[2,2],[2,2],[2,2],[2,2],[2,2],[1,1]] 1 [[1,1]]",
            "[[-5,4],[-6,-5],[4,6]] 2 [[-5,4],[4,6]]",
            "[[6,10],[-3,3],[-2,5],[0,2]] 3 [[0,2],[-3,3],[-2,5]]",
            "[[1,3],[-2,2],[2,-2]] 2 [[-2,2],[2,-2]]",
            "[[-2,10],[-4,-8],[10,7],[-4,-7]] 3 [[-4,-7],[-4,-8],[-2,10]]"
    }, delimiter = ' ')
    void shouldReturnKClosestPointsToTheSource(String pointsExpr, int k, String expectedExpr) {
        int[][] points = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(pointsExpr, Integer.class));
        int[][] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class));

        int[][] output = sol.kClosest(points, k);

        Arrays.sort(expected, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(output, Comparator.comparingInt(o -> o[0]));
        Fixtures.assertBothMatrixContainsSameItems(points, expected, output);
    }

}