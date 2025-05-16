package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchInSortedRotatedArrayTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[4,5,6,7,0,1,2] 0 4",
            "[4,5,6,7,0,1,2] 3 -1",
            "[4,5,6,7,0,1,2] 4 0",
            "[4,5,6,7,0,1,2] 2 6",
            "[2] 1 -1",
            "[1] 1 0",
    }, delimiter = ' ')
    void should_search_the_index_of_target_value_in_the_provided_sorted_rotated_array(String inputExpr, int target, int expected) {
        int[] input = Fixtures.parse1DArray(inputExpr);

        var sol = new SearchInSortedRotatedArray();
        int output = sol.search(input, target);

        assertEquals(expected, output);
    }

}