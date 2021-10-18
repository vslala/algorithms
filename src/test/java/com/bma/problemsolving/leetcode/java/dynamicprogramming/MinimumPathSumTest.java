package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumPathSumTest {

    private MinimumPathSum sol = new MinimumPathSum();

    @ParameterizedTest
    @CsvSource({
            "[[1_3_1]:[1_5_1]:[4_2_1]], 7",
            "[[1_2_3]:[4_5_6]], 12"
    })
    void shouldReturnTheMinimumSumFromTopLeftToTheBottomRight(String inputStr, int expected) {
        var nums = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(inputStr));
        var result = sol.minPathSum(nums);
        assertEquals(expected, result);
    }

}