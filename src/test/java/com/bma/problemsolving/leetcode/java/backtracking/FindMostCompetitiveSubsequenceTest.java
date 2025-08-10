package com.bma.problemsolving.leetcode.java.backtracking;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 19/05/2025
 */
class FindMostCompetitiveSubsequenceTest {

    @Data
    static class TestInput {
        int[] nums;
        int k;
        int[] expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "nums": [3,5,2,6],
                        "k": 2,
                        "expected": [2,6]
                    }
                    """,
            """
                    {
                        "nums": [2,4,3,3,5,4,9,6],
                        "k": 4,
                        "expected": [2,3,3,4]
                    }
                    """
    })
    void should_return_the_most_competitive_subsequence(TestInput testInput) {
        var sol = new FindMostCompetitiveSubsequence();
        int[] output = sol.mostCompetitive(testInput.nums, testInput.k);
        assertArrayEquals(testInput.expected, output);
    }
}