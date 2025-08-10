package com.bma.problemsolving.leetcode.java.dailychallenge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 07/06/2025
 */
class LexicographicallyMinimumStringAfterRemovingStarsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "aaba*, aab",
            "abc, abc",
            "abc*de*fgh*, defgh",
            "a*b*c*d*, _",
            "abcde*f*, cdef",
            "abc*, bc",
            "aaaa***, a",

    })
    void should_return_the_lexicographically_minimum_string_after_removing_stars(String s, String expected) {
        if (expected.equals("_")) {
            expected = "";
        }
        var sol = new LexicographicallyMinimumStringAfterRemovingStars();
        String output = sol.clearStars(s);
        assertEquals(expected, output);
    }

}