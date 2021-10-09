package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniquePathsTwoWithObstaclesTest {

    private UniquePathsTwoWithObstacles uniquePathsTwoWithObstacles = new UniquePathsTwoWithObstacles();

    @ParameterizedTest
    @CsvSource({
            "0_0_0:0_1_0:0_0_0, 2",
            "0_1:0_0, 1",
            "0_0:0_1, 0",
            "0_0:1_1:0_0, 0",
            "0_1, 0",
            "1_0, 0",
            "0_0, 1"
    })
    void shouldComputeAllUniquePathsFromTopLeftToTheBottomRightCell(String matrix, int expected) {
        var rows = matrix.split(":");
        var m = rows.length;
        var n = Fixtures.splitAndParseArr(rows[0], "_").length;
        var inputMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            var row = Fixtures.splitAndParseArr(rows[i], "_");
            for (int j = 0; j < n; j++) {
                inputMatrix[i][j] = row[j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(inputMatrix[i][j] + "|");
            }
            System.out.println();
        }

        assertEquals(expected, uniquePathsTwoWithObstacles.uniquePathsWithObstacles(inputMatrix));
    }
}