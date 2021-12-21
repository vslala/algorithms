package com.bma.problemsolving.leetcode.java.shortestpath;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestIncreasingSubsequenceTest {

    private LongestIncreasingSubsequence cut;

    @ParameterizedTest
    @CsvSource({
            "10_9_2_5_3_7_101_18, 4",
            "0_1_0_3_2_3, 4",
            "7_7_7_7_7_7_7, 1"
    })
    void shouldReturnTheLengthOfLongestIncreasingSubsequence(String inputStr, int expected) {
        int[] nums = Fixtures.splitAndParseArr(inputStr, "_");

        int result = new LongestIncreasingSubsequence(nums).longestLIS();

        assertEquals(expected, result);
    }

}