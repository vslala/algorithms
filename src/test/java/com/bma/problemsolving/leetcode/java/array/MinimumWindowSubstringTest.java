package com.bma.problemsolving.leetcode.java.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumWindowSubstringTest {

    private MinimumWindowSubstring sol = new MinimumWindowSubstring();

    @ParameterizedTest()
    @CsvSource({
            "ADOBECODEBANC, ABC, BANC",
            "a, a, a",
            "a, aa, ''",
    })
    void shouldReturnMinimumSubstringContainingTheDesiredString(String s, String t, String expected) {
        var result = sol.minWindow(s, t);
        assertEquals(expected, result);
    }

}