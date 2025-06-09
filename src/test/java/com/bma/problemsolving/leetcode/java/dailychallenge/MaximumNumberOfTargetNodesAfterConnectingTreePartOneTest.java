package com.bma.problemsolving.leetcode.java.dailychallenge;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 28/05/2025
 */
class MaximumNumberOfTargetNodesAfterConnectingTreePartOneTest {

    @Data
    static class Input {
        int[][] edges1;
        int[][] edges2;
        int k;
        int[] expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "edges1": [[0,1],[0,2],[2,3],[2,4]],
                        "edges2": [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]],
                        "k": 2,
                        "expected": [9,7,9,8,8]
                    }
                    """,
            """
                    {
                        "edges1": [[0,1],[0,2],[0,3],[0,4]],
                        "edges2": [[0,1],[1,2],[2,3]],
                        "k": 1,
                        "expected": [6,3,3,3,3]
                    }
                    """
    })
    void it_should_return_the_maximum_edges_from_each_node_after_traversing_k_levels(Input input) {
        var sol = new MaximumNumberOfTargetNodesAfterConnectingTreePartOne();
        int[] output = sol.maxTargetNodes(input.edges1, input.edges2, input.k);
        assertArrayEquals(input.expected, output);
    }

}