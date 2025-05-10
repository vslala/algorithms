package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindMinimumInRotatedArrayTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[3,4,5,1,2] 1",
            "[4,5,6,7,0,1,2] 0",
            "[11,13,15,17] 11"
    }, delimiter = ' ')
    void should_return_the_minimum_number_in_a_rotated_array(String inputExpr, int expected) {
        int[] input = Fixtures.parse1DArray(inputExpr);

        var sol = new FindMinimumInRotatedArray();
        int output = sol.findMin(input);

        assertEquals(expected, output);
    }

}