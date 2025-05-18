package com.bma.problemsolving.leetcode.java.blind75;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 17/05/2025
 */
class NumberOfIslandTest {

    @Data
    static class IslandInput {
        private char[][] grid;
        private int expected;
    }


    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "grid": [
                          ["1","1","1","1","0"],
                          ["1","1","0","1","0"],
                          ["1","1","0","0","0"],
                          ["0","0","0","0","0"]
                        ],
                        "expected": 1
                    }
                    """,
            """
                    {
                        "grid": [
                            ["1","1","0","0","0"],
                            ["1","1","0","0","0"],
                            ["0","0","1","0","0"],
                            ["0","0","0","1","1"]
                        ],
                        "expected": 3
                    }
                    """
    })
    void should_return_the_number_of_islands_covered_by_water(IslandInput input) throws IOException {
        var sol = new NumberOfIsland();
        int output = sol.numIslands(input.grid);

        assertEquals(input.expected, output);
    }
}