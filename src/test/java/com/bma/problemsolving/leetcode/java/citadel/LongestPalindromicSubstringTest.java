package com.bma.problemsolving.leetcode.java.citadel;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestPalindromicSubstringTest {

    private LongestPalindromicSubstring sol;

    @ParameterizedTest
    @CsvSource(value = {
            "babad aba",
            "cbbd bb",
            "asdfabbbbaasdf abbbba"
    }, delimiter = ' ')
    void it_should_return_the_longest_palindromic_substring(String input, String expected) {
        sol = new LongestPalindromicSubstring();
        String output = sol.longestPalindrome(input);
        assertEquals(expected, output);
    }
}