package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NonOverlappingIntervalsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2],[2,3],[3,4],[1,3]] 1",
            "[[1,2],[1,2],[1,2]] 2",
            "[[1,2],[2,3]] 0"
    }, delimiter = ' ')
    void given_a_list_of_intervals_return_the_count_of_intervals_that_needs_to_be_removed(String inputExpr, int expected) {
        int[][] input = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class));

        var sol = new NonOverlappingIntervals();
        int output = sol.eraseOverlapIntervals(input);

        assertEquals(expected, output);
    }
}