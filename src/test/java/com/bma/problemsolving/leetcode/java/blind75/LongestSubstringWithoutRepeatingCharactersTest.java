package com.bma.problemsolving.leetcode.java.blind75;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringWithoutRepeatingCharactersTest {

    @ParameterizedTest
    @CsvSource(value = {
            "abcabcbb 3",
            "bbbbb 1",
            "pwwkew 3",
            "asdfzendnasdfzendl 7",
            "a 1",
            "au 2"
    }, delimiter = ' ')
    void it_should_return_longest_substring_without_repeating_characters(String str, int expected) {
        var sol = new LongestSubstringWithoutRepeatingCharacters();
        int output = sol.lengthOfLongestSubstring(str);
        assertEquals(expected, output);
    }

}