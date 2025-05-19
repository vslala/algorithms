package com.bma.problemsolving.leetcode.java.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 18/05/2025
 */
class StringMatchingTest {

    @ParameterizedTest
    @CsvSource(value = {
            "mississippi, issi, 1",
            "mississippi, issip, 4",
            "sadbutsad, sad, 0",
            "leetcode, leeto, -1",
            "aaa, aaaa, -1"
    })
    void should_find_the_needle_in_the_haystack(String haystack, String needle, int expected) {
        var sol = new StringMatching();
        int output = sol.strStr(haystack, needle);
        assertEquals(expected, output);
    }
}