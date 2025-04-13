package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringWithUniqueCharactersTest {

    @ParameterizedTest
    @CsvSource({
            "pwwkew, 3", "bbbbb, 1", "abcabcbb, 3", "ckilbkd,5", "au,2"
    })
    void givenAStringFindTheLongestSubstringWithoutRepeatingCharacters(String str, int expected) {
        System.out.println(str + "," + expected);
        var test = new LongestSubstringWithUniqueCharacters();
        int len = test.lengthOfLongestSubstring(str);
        assertEquals(expected, len);
    }
}