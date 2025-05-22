package com.bma.problemsolving.leetcode.java.dailychallenge;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 22/05/2025
 */
class ZeroArrayTransformationsPartThreeTest {

    @Data
    static class Input {
        int[] nums;
        int[][] queries;
        int expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "nums": [2,0,2],
                        "queries": [[0,2],[0,2],[1,1]],
                        "expected": 1
                    }
                    """,
            """
                    {
                        "nums": [1,1,1,1],
                        "queries": [[1,3],[0,2],[1,3],[1,2]],
                        "expected": 2
                    }
                    """,
            """
                    {
                        "nums": [1,2,3,4],
                        "queries": [[0,3]],
                        "expected": -1
                    }
                    """
    })
    void it_should_provide_the_max_number_of_elements_that_can_be_removed_from_the_query(Input input) {
        var sol = new ZeroArrayTransformationsPartThree();
        int output = sol.maxRemoval(input.nums, input.queries);
        assertEquals(input.expected, output);
    }
}