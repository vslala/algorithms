package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TopKFrequentElementsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[1,1,1,2,2,3] 2 [1,2]",
            "[1,1,1,2,2,3,3,3,3,3,4,4,5,5,5,5,7,7,7,8,8,9,9,9,9,9,9,9,10,10,10] 2 [3,9]",
            "[5,-3,9,1,7,7,9,10,2,2,10,10,3,-1,3,7,-9,-1,3,3] 3 [3,7,10]"
    }, delimiter = ' ')
    void should_return_the_top_k_frequent_elements(String inputExpr, int k, String expectedExpr) {
        int[] input = Fixtures.parse1DArray(inputExpr);
        int[] expected = Fixtures.parse1DArray(expectedExpr);

        var sol = new TopKFrequentElements();
        int[] output = sol.topKFrequent(input, k);

        Fixtures.assertArrayContainsSameItems(expected, output);
    }

}