package com.bma.problemsolving.leetcode.java.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermutationInStringTest {

    private PermutationInString sol = new PermutationInString();

    @ParameterizedTest
    @CsvSource({
            "ab, eidbaooo, true",
            "ab, eidboaoo, false",
            "adc, dcda, true",
            "abcdxabcde, abcdeabcdx, true",
            "sea, ate, false"
    })
    void shouldReturnTrueIfStringTwoContainsThePermutationOfStringOne(String s1, String s2, boolean expected) {
        assertEquals(expected, sol.checkInclusion(s1, s2));
        assertEquals(expected, sol.checkInclusionWithArrayAsHashMap(s1, s2));
    }


}