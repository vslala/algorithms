package com.bma.problemsolving.leetcode.java.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseStringTest {

    private ReverseString sol = new ReverseString();

    @ParameterizedTest
    @CsvSource({
            "hello, olleh",
            "hannah, hannah"
    })
    void shouldReverseTheGivenStringByInPlaceSwap(String s, String expected) {
        var input = s.toCharArray();
        sol.reverseString(input);
        assertEquals(String.valueOf(input), expected);
    }

    @ParameterizedTest
    @CsvSource({
            "hello, olleh",
            "hannah, hannah"
    })
    void shouldReverseTheGivenStringByInPlaceSwapRecursively(String s, String expected) {
        var input = s.toCharArray();
        sol.reverseStringRecursive(input);
        assertEquals(String.valueOf(input), expected);
    }

}