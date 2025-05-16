package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InsertIntervalTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,3],[6,9]] [2,5] [[1,5],[6,9]]",
            "[[1,2],[3,5],[6,7],[8,10],[12,16]] [4,5] [[1,2],[3,5],[6,7],[8,10],[12,16]]",
            "[[1,2],[3,5],[6,7],[8,10],[12,16]] [4,8] [[1,2],[3,10],[12,16]]",
            "[] [5,7] [[5,7]]",
            "[[1,5]] [0,3] [[0,5]]"
    }, delimiter = ' ')
    void given_an_interval_insert_it_into_a_list_of_sorted_intervals_such_that_the_intervals_do_not_overlap(String intervalsExpr, String newIntervalExpr, String expectedIntervalsExpr) {
        int[][] intervals = Fixtures.parse2DArray(intervalsExpr);
        int[] newInterval = Fixtures.parse1DArray(newIntervalExpr);
        int[][] expected = Fixtures.parse2DArray(expectedIntervalsExpr);

        var sol = new InsertInterval();
        int[][] output = sol.insert(intervals, newInterval);

        Fixtures.assertBothMatrixContainsSameItems(intervals, expected, output);
    }
}