package com.bma.problemsolving.leetcode.java.blind75;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MaxOccurrencesSubstringTest {

    @ParameterizedTest
    @CsvSource(value = {
            "aababcaab,2,3,4,2",
            "aaaa,1,3,3,2"
    })
    void should_return_max_number_of_occurrences_of_substring(String s, int maxLetters, int minSize, int maxSize, int expected) {
        var sol = new MaxOccurrencesSubstring();
        int output = sol.maxFreq(s, maxLetters, minSize, maxSize);

        assertEquals(expected, output);
    }

}