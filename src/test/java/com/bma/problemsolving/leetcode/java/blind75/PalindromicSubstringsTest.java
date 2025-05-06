package com.bma.problemsolving.leetcode.java.blind75;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PalindromicSubstringsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "abc 3",
            "aaa 6",
    }, delimiter = ' ')
    void it_should_return_the_count_of_palindromic_substrings(String input, int expected) {
        var sol = new PalindromicSubstrings();
        int output = sol.countSubstrings(input);

        assertEquals(expected, output);
    }
}