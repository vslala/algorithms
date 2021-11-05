package com.bma.problemsolving.leetcode.java.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringWithoutRepeatingCharactersTest {

    private LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();

    @ParameterizedTest
    @CsvSource({
            "abcabcbb, 3",
            "bbbbb, 1",
            "pwwkew, 3",
            ", 0",
            "_, 1",
            "dvdf, 3",
            "anviaj, 5"
    })
    void shouldReturnTheSizeOfLongestSubstringWithoutRepeatingCharacters(String input, int expected) {
        input = Objects.isNull(input) ? ""
                : input.equals("_") ? " "
                : input;
        assertEquals(expected, sol.lengthOfLongestSubstring(input));
    }
}