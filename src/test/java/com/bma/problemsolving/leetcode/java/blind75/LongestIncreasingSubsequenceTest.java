package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingSubsequenceTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[10,9,2,5,3,7,101,18] 4",
            "[0,1,0,3,2,3] 4",
            "[7,7,7,7,7,7,7] 1",
            "[1,2,2,3,4,4,5] 5",
            "[9,8,7,6,5] 1"
    }, delimiter = ' ')
    void should_return_the_longest_increasing_subsequence(String inputExpr, int expected) {
        int[] input = Fixtures.parse1DArray(inputExpr);

        var sol = new LongestIncreasingSubsequence();
        int output = sol.lengthOfLIS(input);

        assertEquals(expected, output);
    }
}