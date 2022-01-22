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
            "7_7_7_7_7_7_7, 1",
            "6_2_5_1_7_4_8_3, 4"
    })
    void shouldReturnTheLengthOfLongestIncreasingSubsequence(String inputStr, int expected) {
        int[] nums = Fixtures.splitAndParseArr(inputStr, "_");

        int result = LongestIncreasingSubsequence.get(LongestIncreasingSubsequence.Algorithm.DYNAMIC_PROGRAMMING).longestLIS(nums);
        int result_ = LongestIncreasingSubsequence.get(LongestIncreasingSubsequence.Algorithm.DEPTH_FIRST_SEARCH).longestLIS(nums);

        assertEquals(expected, result);
        assertEquals(result, result_);
    }

}