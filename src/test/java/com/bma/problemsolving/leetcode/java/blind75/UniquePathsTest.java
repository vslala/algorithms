package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.problemsolving.Timer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 16/05/2025
 */
class UniquePathsTest {

    @ParameterizedTest
    @CsvSource({
            "1,1, 1",
            "3,7, 28",
            "3,2, 3",
            "3,3, 6",
            "15,10, 817190"
    })
    void shouldComputeAllUniquePathsFromTopLeftCornerOfGridToBottomRightCorner(int m, int n, int expected) {
        var sol = new UniquePaths();
        System.out.println("Dynamic Programming");
        System.out.println(Timer.timeIt(() -> assertEquals(expected, sol.uniquePaths(m, n))) + "ms");
    }
}