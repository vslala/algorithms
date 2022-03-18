package com.bma.problemsolving.meta.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Comparator;

class MergeIntervalsTest {
    private MergeIntervals mergeIntervals = new MergeIntervals();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,3],[2,6],[8,10],[15,18]] [[1,6],[8,10],[15,18]]",
            "[[1,4],[4,5]] [[1,5]]",
            "[[1,4],[4,5]] [[1,5]]",
            "[[1,4],[0,4]] [[0,4]]",
            "[[1,4],[0,0]] [[0,0],[1,4]]",
            "[[2,3],[4,5],[6,7],[8,9],[1,10]] [[1,10]]",
            "[[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]] [[1,3],[4,7]]"
    }, delimiter = ' ')
    void shouldMergeOverlappingIntervals(String inputExpr, String expectedExpr) {
        int[][] intervals = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class));
        int[][] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class));

        int[][] output = mergeIntervals.merge(intervals);

        Arrays.sort(output, (Comparator.comparingInt(o -> o[0])));
        Fixtures.assertBothMatrixContainsSameItems(intervals, expected, output);
    }
}