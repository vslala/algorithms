package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeIntervalsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,3],[2,6],[8,10],[15,18]] [[1,6],[8,10],[15,18]]",
            "[[1,4],[4,5]] [[1,5]]"
    }, delimiter = ' ')
    void should_merge_all_overlapping_intervals(String intervalsExpr, String expectedExpr) {
        int[][] input = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(intervalsExpr, Integer.class));
        int[][] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class));

        var sol = new MergeIntervals();
        int[][] output = sol.merge(input);

        assertArrayEquals(expected, output);

    }
}