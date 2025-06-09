package com.bma.problemsolving.leetcode.java.dailychallenge;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 30/05/2025
 */
class FindClosestNodeToGivenTwoNodesTest {
    @Data
    private static class Input {
        int[] edges;
        int node1;
        int node2;
        int expected;
    }
    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "edges": [2,2,3,-1],
                        "node1": 0,
                        "node2": 1,
                        "expected": 2
                    }
                    """,
            """
                    {
                        "edges": [1,2,-1],
                        "node1": 0,
                        "node2": 2,
                        "expected": 2
                    }
                    """,
            """
                    {
                        "edges": [4,4,8,-1,9,8,4,4,1,1],
                        "node1": 5,
                        "node2": 6,
                        "expected": 1
                    }
                    """,
            """
                    {
                        "edges": [5,3,1,0,2,4,5],
                        "node1": 1,
                        "node2": 6,
                        "expected": 5
                    }
                    """,
            """
                    {
                        "edges": [1,2,0,2,3,4,5],
                        "node1": 0,
                        "node2": 6,
                        "expected": 2
                    }
                    """,
            """
                    {
                        "edges": [5,4,5,4,3,6,-1],
                        "node1": 0,
                        "node2": 1,
                        "expected": -1
                    }
                    """,
            """
                    {
                        "edges": [-1,3,-1,1,1,3,3,-1],
                        "node1": 0,
                        "node2": 1,
                        "expected": -1
                    }
                    """,

    })
    void should_return_the_closest_node_to_the_given_two_nodes(Input input) {
        var sol = new FindClosestNodeToGivenTwoNodes();
        int output = sol.closestMeetingNode(input.edges, input.node1, input.node2);
        assertEquals(input.expected, output);
    }

}