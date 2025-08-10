package com.bma.problemsolving.leetcode.java.dailychallenge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 09/06/2025
 */
class KSmallestInLexicographicalOrderTest {
    @ParameterizedTest
    @CsvSource(value = {
            "13 2 10",
            "2 1 1",
            "13 6 2",
            "2 2 2",
            "911 22 118",
            "1 1 1",
            "10 5 4",
            "50 15 22",
            "1692778 1545580 867519",
            "804289384 42641503 138377349",
            "681692778 351251360 416126219"
    }, delimiter = ' ')
    void it_should_provide_the_kth_smallest_lexicographical_number_within_the_given_limit(int limit, int k, int expected) {
        var sol = new KSmallestInLexicographicalOrder();
        int output = sol.findKthNumber(limit, k);
        assertEquals(expected, output);
    }
}