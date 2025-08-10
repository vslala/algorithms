package com.bma.problemsolving.leetcode.java.dailychallenge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 05/06/2025
 */
class LexicographicallySmallestEquivalentStringTest {

    @ParameterizedTest
    @CsvSource(value = {
            "parker, morris, parser, makkek",
            "hello, world, hold, hdld",
            "leetcode, programs, sourcecode, aauaaaaada"
    })
    void it_should_return_lexicographically_smallest_equivalent_string(String s1, String s2, String baseStr, String expected) {
        var sol = new LexicographicallySmallestEquivalentString();
        String output = sol.smallestEquivalentString(s1, s2, baseStr);
        assertEquals(expected, output);
    }

}