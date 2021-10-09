package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.problemsolving.Timer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniquePathsTest {

    private UniquePaths uniquePaths = new UniquePaths();

    @ParameterizedTest
    @CsvSource({
            "1,1, 1",
            "3,7, 28",
            "3,2, 3",
            "3,3, 6",
            "15,10, 817190"
    })
    void shouldComputeAllUniquePathsFromTopLeftCornerOfGridToBottomRightCorner(int m, int n, int expected) {
        // dynamic programming
        System.out.println("Dynamic Programming");
        System.out.println(Timer.timeIt(() -> assertEquals(expected, uniquePaths.uniquePaths(m, n))) + "ms");

        // recursive starting from bottom right corner
        System.out.println("Recusive Solution");
        System.out.println(Timer.timeIt(() -> assertEquals(expected, uniquePaths.findUniquePaths(m, n))) + "ms");
        System.out.println("----------------------------------");

    }

}