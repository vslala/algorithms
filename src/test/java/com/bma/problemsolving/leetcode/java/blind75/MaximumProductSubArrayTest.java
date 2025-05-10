package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MaximumProductSubArrayTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[2,3,-2,4] 6",
            "[-2,0,-1] 0",
            "[1,1,1] 1"
    }, delimiter = ' ')
    void should_return_the_max_product_of_the_subarray(String inputExpr, int expected) {
        int[] input = Fixtures.parse1DArray(inputExpr);

        var sol = new MaximumProductSubArray();
        int output = sol.maxProduct(input);

        assertEquals(expected, output);
    }

}