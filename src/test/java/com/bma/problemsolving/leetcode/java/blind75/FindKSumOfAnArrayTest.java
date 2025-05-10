package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FindKSumOfAnArrayTest {

    @ParameterizedTest
    @CsvSource(value={
            "[2,4,-2] 5 2",
            "[1,-2,3,4,-10,12] 16 10",
            "[-487322177,-656480132,850198596,-291605661,-272668395,110865952,-162529283,-145886963,202657909,125667049,-282090943,120877054,-85849348,-482630078,-802177895,-574862206,374637017,804297842] 1707 1493582115"
    }, delimiter = ' ')
    void should_find_the_kth_largest_subsequence_sum_of_an_array(String inputExpr, int k, long expected) {
        int[] input = Fixtures.parse1DArray(inputExpr);

        var sol = new FindKSumOfAnArray();
        long output = sol.kSum(input, k);

        assertEquals(expected, output);
    }
}