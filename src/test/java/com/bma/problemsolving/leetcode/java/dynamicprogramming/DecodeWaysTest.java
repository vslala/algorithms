package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecodeWaysTest {
    private DecodeWays sol = new DecodeWays();

    @ParameterizedTest
    @CsvSource({
            "12, 2",
            "226, 3",
            "0, 0"
    })
    void shouldReturnNumberOfWaysToDecodeTheString(String input, int expected) {
        var result = sol.numDecodings(input);
        assertEquals(expected, result);
    }

}