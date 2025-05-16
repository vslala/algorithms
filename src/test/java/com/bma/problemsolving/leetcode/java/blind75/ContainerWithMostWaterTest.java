package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerWithMostWaterTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,8,6,2,5,4,8,3,7]] 49",
            "[[1,1]] 1",
            "[[4,3,2,1,4]] 16",
            "[[1,2,1]] 2",
            "[[1,2,4,3]] 4",
            "[[6,9,3,4,5,8]] 32",
            "[[2,3,10,5,7,8,9]] 36",
            "[[1,2,1,3,1,2,1]] 8",
            "[[5,5,5,5,5,5,5,5]] 35",
            "[[9,8,7,6,5,4,3,2,1]] 20",
            "[[1,2,3,4,5,6,7,8,9]] 20",
            "[[1,3,2,5,25,24,5]] 24",
            "[[1,1,1,1,50,1,1,1,1]] 8",
            "[[1,1,100,1,1]] 4",
            "[[100,1,1,1,100]] 400",
            "[[1,2,1,1,100,1]] 6",
            "[[10,9,8,7,6,5,4,3,2,1]] 25",
            "[[1,2,3,4,5,4,3,2,1]] 12",
            "[[2,3,4,5,18,17,6]] 17",
            "[[5,2,12,1,5,3,4,11,9,4]] 55"
    }, delimiter = ' ')
    void it_should_return_the_area_of_the_container_to_store_the_maximum_amount_of_water(String input, int expected) {
        int[][] heights = Fixtures.convertToPrimitiveArrMatrix(LeetCodeInputExpressionParser.parseNestedArrExpression(input, ',', Integer.class));
        var sol = new ContainerWithMostWater();
        int output = sol.maxArea(heights[0]);

        assertEquals(expected, output);
    }


}