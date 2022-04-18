package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContinuousSubarraySumTest {

    private ContinuousSubarraySum sol = new ContinuousSubarraySum();

    @ParameterizedTest
    @CsvSource(value = {
            "[[23,2,4,6,7]] 6 true",
            "[[23,2,6,4,7]] 6 true",
            "[[23,2,6,4,7]] 13 false",
            "[[23,2,4,6,6]] 7 true"
    }, delimiter = ' ')
    void shouldReturnTrueIfNumsHasAContinuousSubarrayOfSizeAtLeastTwoWhoseElementsSumUpToAMultipleOfKOrFalseOtherwise(String arrExpr, int k, boolean expected) {
        int[] input = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(arrExpr, Integer.class))[0];

        boolean output = sol.checkSubarraySum(input, k);

        assertEquals(expected, output);
    }

}