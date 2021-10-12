package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermutationsTest {

    private Permutations permutations = new Permutations();

    @ParameterizedTest
    @CsvSource({
            "1_2_3, [[1_2_3]:[1_3_2]:[2_1_3]:[2_3_1]:[3_1_2]:[3_2_1]]",
            "0_1, [[0_1]:[1_0]]",
            "1, [[1]]"
    })
    void shouldReturnAllUniquePermutations(String inputStr, String expectedOutput) {
        List<List<Integer>> expected = Fixtures.parseExpression(expectedOutput);
        var input = Fixtures.splitAndParseArr(inputStr, "_");

        Util.println(expected);
        var result = permutations.permute(input);
        Util.println(result);

        assertEquals(expected.size(), result.size());
        Fixtures.assertBothListsContainsSameItems(expected, result);
    }

}