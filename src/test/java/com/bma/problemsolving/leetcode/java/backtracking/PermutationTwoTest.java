package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermutationTwoTest {

    private PermutationTwo sol = new PermutationTwo();

    @ParameterizedTest
    @CsvSource({
            "1_1_2, [[1_1_2]:[1_2_1]:[2_1_1]]",
            "1_2_3, [[1_2_3]:[1_3_2]:[2_1_3]:[2_3_1]:[3_1_2]:[3_2_1]]",
    })
    void shouldReturnAllTheUniqueCombinationsOftheGivenArray(String inputArrStr, String expectedArrStr) {
        var input = Fixtures.splitAndParseArr(inputArrStr, "_");
        List<List<Integer>> expected = Fixtures.parseExpression(expectedArrStr);

        var result = sol.permuteUnique(input);

        assertEquals(expected.size(), result.size());
        Fixtures.assertBothListsContainsSameItems(expected, result);
    }

}