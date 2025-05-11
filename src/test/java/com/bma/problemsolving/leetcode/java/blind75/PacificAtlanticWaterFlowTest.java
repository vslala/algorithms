package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class PacificAtlanticWaterFlowTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]] [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]",
            "[[1]] [[0,0]]"
    }, delimiter = ' ')
    void should_return_a_2d_list_of_grid_coordinates_from_where_rain_water_can_flow_to_both_pacific_and_atlantic_ocean(String heightsExpr, String expectedExpr) {
        int[][] heights = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(heightsExpr, Integer.class));
        List<List<Integer>> expected = Fixtures.parseNestedArrExpression(expectedExpr, Integer.class);

        var sol = new PacificAtlanticWaterFlow();
        List<List<Integer>> output = sol.pacificAtlantic(heights);

        Fixtures.assertBothListsContainsSameItems(expected, output);
    }

}