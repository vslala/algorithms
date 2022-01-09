package com.bma.problemsolving.leetcode.java.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountBinarySubstringsTest {

    private CountBinarySubstrings sol = new CountBinarySubstrings();

    @ParameterizedTest
    @CsvSource({
            "00110011, 6",
            "101010, 5",
            "10101, 4",
            "000000, 0",
            "000111, 3",
    })
    void shouldReturnTheCountOfBinarySubstrings(String input, int expected) {
        var result = sol.count(input);

        assertEquals(expected, result);
    }
}