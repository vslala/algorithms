package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxAreaOfIslandTest {
    
    private MaxAreaOfIsland sol = new MaxAreaOfIsland();
    
    @ParameterizedTest
    @CsvSource({
            "[[0_0_1_0_0_0_0_1_0_0_0_0_0]:[0_0_0_0_0_0_0_1_1_1_0_0_0]:[0_1_1_0_1_0_0_0_0_0_0_0_0]:[0_1_0_0_1_1_0_0_1_0_1_0_0]:[0_1_0_0_1_1_0_0_1_1_1_0_0]:[0_0_0_0_0_0_0_0_0_0_1_0_0]:[0_0_0_0_0_0_0_1_1_1_0_0_0]:[0_0_0_0_0_0_0_1_1_0_0_0_0]], 6",
            "[[0_0_0_0_0_0_0_0]], 0"
    })
    void shouldCalcTheLargestIslandByArea(String inputValue, int expected) {
        var matrix = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(inputValue));

//        var output = sol.maxAreaOfIslandDfs(matrix);
        var output = sol.maxAreaOfIslandBfs(matrix);

        assertEquals(expected, output);
    }
    

}