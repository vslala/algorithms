package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RangeAdditionTest {

    private RangeAddition sol = new RangeAddition();

    @ParameterizedTest
    @CsvSource(value = {
            "5 [[1,3,2],[2,4,3],[0,2,-2]] [[-2,0,3,5,3]]",
            "10 [[2,4,6],[5,6,8],[1,9,-4]] [[0,-4,2,2,2,4,4,-4,-4,-4]]"
    }, delimiter = ' ')
    void shouldReturnModifiedArrayWithCorrectRangeAdditions(int length, String inputExpr, String expectedExpr) {
        int[][] input = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class));
        int[][] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class));

        int[] output = sol.getModifiedArray(length, input);

        Fixtures.assertArrayEquals(output, expected[0], output);
    }

}