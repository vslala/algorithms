package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestPalindromicSubstringTest {

    private LongestPalindromicSubstring sol = new LongestPalindromicSubstring();

    @ParameterizedTest
    @CsvSource({
            "babad, bab",
            "cbbd, bb",
            "a, a",
            "ac, a"
    })
    void shouldReturnTheLongestPalindromicSubstring(String input, String expectedOutput) {
        assertEquals(expectedOutput, sol.longestPalindrome(input));
    }

    @ParameterizedTest
    @CsvSource({
            "dbabccbazz,abccba",
            "babad, bab",
            "cbbd, bb",
            "a, a",
            "ac, a"
    })
    void shouldReturnTheLongestPalindromicSubstringUsingDynamicProgramming(String input, String expectedOutput) {
        assertEquals(expectedOutput, sol.longestPalindromeDP(input));
    }

    @ParameterizedTest
    @CsvSource({
            "dbabccbazz,abccba",
            "babad, bab",
            "cbbd, bb",
            "a, a",
            "ac, a"
    })
    void shouldReturnTheLongestPalindromicSubstringUsingRecursiveMemoizationProgramming(String input, String expectedOutput) {
        assertEquals(expectedOutput, sol.longestPalindromeRecursive(input));
    }

}