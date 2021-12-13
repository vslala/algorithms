package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumSubArrayTest {

    private MaximumSubArray sol = new MaximumSubArray();

    @ParameterizedTest
    @CsvSource({
            "-2_1_-3_4_-1_2_1_-5_4, 6",
            "1, 1",
            "5_4_-1_7_8, 23",
            "-1_-2_-3, -1"
    })
    void shouldReturnTheSumOfMaximumSubarray(String inputArrStr, int expected) {
        int[] input = Fixtures.splitAndParseArr(inputArrStr, "_");

        int result = sol.maxSubArraySum(input);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
//            "-2_1_-3_4_-1_2_1_-5_4, 4_-1_2_1",
//            "1, 1",
//            "5_4_-1_7_8, 5_4_-1_7_8",
//            "-1, -1",
//            "1_2_3_4_5_6_7_-20, 1_2_3_4_5_6_7",
//            "-1_-2_-3, -1",
            "4_-1_-1_-1_-1_-1, 4"
    })
    void shouldReturnTheMaximumSubarray(String inputArrStr, String expectedArrStr) {
        int[] input = Fixtures.splitAndParseArr(inputArrStr, "_");
        int[] expected = Fixtures.splitAndParseArr(expectedArrStr, "_");

        int[] result = sol.maxSubArray(input);

        Fixtures.assertArrayEquals(input, expected, result);
    }

}