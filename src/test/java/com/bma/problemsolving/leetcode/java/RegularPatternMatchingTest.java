package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularPatternMatchingTest {

    RegularPatternMatching regex = new RegularPatternMatching();

    @ParameterizedTest
    @CsvSource({
            "ss, s, false",
            "ss, .s, true",
            "ss, s., true"
    })
    void itShouldMatchAnySingleCharacter(String input, String pattern, boolean expected) {
        var patternMatcher = new RegularPatternMatching();
        assertEquals(expected, patternMatcher.isMatch(input, pattern));
    }

}