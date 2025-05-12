package com.bma.problemsolving.leetcode.java.blind75;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LongestRepeatingCharacterReplacementTest {

    @ParameterizedTest
    @CsvSource(value = {
            "ABAB 2 4",
            "AABABBA 1 4",
            "AAAAB 1 5",
            "AAABBBAA 3 8",
            "AABBABBAAAAACCCDAAA 1 6"
    }, delimiter = ' ')
    void given_a_substring_it_should_find_the_longest_substring_that_could_contain_the_same_character_after_replacing_k_characters(String input, int k, int expected) {
        var sol = new LongestRepeatingCharacterReplacement();
        int output = sol.characterReplacement(input, k);

        assertEquals(expected, output);
    }
}