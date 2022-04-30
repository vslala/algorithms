package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumSizeSubarraySumEqualsKTest {

    private MaximumSizeSubarraySumEqualsK sol = new MaximumSizeSubarraySumEqualsK();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,-1,5,-2,3]] 3 4",
            "[[-2,-1,2,1]] 1 2",
            "[[1]] 0 0",
            "[[-5,8,2,-1,6,-3,7,1,8,-2,7]] -4 0",
            "[[-1,1]] -1 1",
            "[[0,0]] 0 2",
    }, delimiter = ' ')
    void shouldReturnTheMaximumLengthOfSubarraySumEqualsK(String arrExpr, int k, int expected) {
        int[] input = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(arrExpr, Integer.class))[0];

        int output = sol.maxSubArrayLen(input, k);

        assertEquals(expected, output);
    }
}