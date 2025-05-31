package com.bma.problemsolving.leetcode.java.dailychallenge;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 29/05/2025
 */
class MaximumNumberOfTargetNodesAfterConnectingTreePartTwoTest {
    @Data
    static class Input {
        int[][] edges1;
        int[][] edges2;
        int[] expected;
    }
    /**
     * Node u is target to node v if the number of edges on the path from u to v is even.
     * Note that a node is always target to itself.
     */
    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "edges1": [[0,1],[0,2],[2,3],[2,4]],
                        "edges2": [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]],
                        "expected": [8,7,7,8,8]
                    }
                    """,
            """
                    {
                        "edges1": [[0,1],[0,2],[0,3],[0,4]],
                        "edges2": [[0,1],[1,2],[2,3]],
                        "expected": [3,6,6,6,6]
                    }
                    """,
    })
    void it_should_return_the_maximum_number_of_target_nodes_as_per_target_node_definition(Input input) {
        var sol = new MaximumNumberOfTargetNodesAfterConnectingTreePartTwo();
        int[] output = sol.maxTargetNodes(input.edges1, input.edges2);
        assertArrayEquals(input.expected, output);
    }
}