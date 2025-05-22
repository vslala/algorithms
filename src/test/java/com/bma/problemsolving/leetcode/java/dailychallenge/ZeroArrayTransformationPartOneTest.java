package com.bma.problemsolving.leetcode.java.dailychallenge;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 20/05/2025
 */
class ZeroArrayTransformationPartOneTest {

    @Data
    static class Input {
        int[] nums;
        int[][] queries;
        boolean expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "nums": [1,0,1],
                        "queries": [[0,2]],
                        "expected": true
                    }
                    """,
            """
                    {
                        "nums": [4,3,2,1],
                        "queries": [[1,3],[0,2]],
                        "expected": false
                    }
                    """,
            """
                    {
                        "nums": [1,0,1,5,1,12,10,8,11,0],
                        "queries": [[0,8], [0,8],[0,3], [0,9]],
                        "expected": false
                    }
                    """
    })
    void should_true_if_its_possible_to_transform_given_array_into_a_zero_array_after_transformations(Input input) {
        var sol = new ZeroArrayTransformationPartOne();
        boolean output = sol.isZeroArray(input.nums, input.queries);
        assertEquals(input.expected, output);
    }


}