package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 16/05/2025
 */
class NumberOfConnectedComponentsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "5 [[0,1],[1,2],[3,4]] 2",
            "5 [[0,1],[1,2],[2,3],[3,4]] 1",
            "2 [[0,1]] 1",
            "7 [[0,1],[1,2],[1,3],[1,4],[5,6] 2",
            "0 [[]] 0"
    }, delimiter = ' ')
    void should_return_the_number_of_connected_components_in_the_given_graph(int n, String edgesExpr, int expected) {
        int[][] edges = Fixtures.parse2DArray(edgesExpr);

        var sol = new NumberOfConnectedComponents();
        int output = sol.countComponents(n, edges);

        assertEquals(expected, output);
    }

}