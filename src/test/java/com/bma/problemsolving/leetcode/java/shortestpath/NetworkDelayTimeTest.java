package com.bma.problemsolving.leetcode.java.shortestpath;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NetworkDelayTimeTest {

    private NetworkDelayTime sol = new NetworkDelayTime();

    @ParameterizedTest
    @CsvSource({
            "[[2_1_1]:[2_3_1]:[3_4_1]], 4, 2, 2",
            "[[1_2_1]], 2, 1, 1",
            "[[1_2_1]], 2, 2, -1",
            "[[1_2_1]:[2_3_7]:[1_3_4]:[2_1_2]], 3, 1, 4"
    })
    void shouldReturnTheTimeItTakesForAllNodesToReceiveSignalStartingFromTheGivenNode(String timesStr, int numOfNodes, int startNode, int expectedDelay) {
        List<List<Integer>> lsTimes = Fixtures.parseExpression(timesStr);
        int[][] times = Fixtures.convertToPrimitiveArrMatrix(lsTimes);

        var result = sol.networkDelayTime(times, numOfNodes, startNode);

        assertEquals(expectedDelay, result);
    }

}