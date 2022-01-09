package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinChangeTest {

    @ParameterizedTest
    @CsvSource({
            "1_2_5, 11, 3",
            "1_2_5, 3, 2",
            "2, 3, -1",
            "1_2_5, 7, 2"
    })
    void shouldReturnTheMinCoinsRequiredToReachTheTarget(String coinsStr, int amount, int expected) {
        int[] coins = Fixtures.splitAndParseArr(coinsStr, "_");

        var dpResult = CoinChange.getAlgorithm("DP").coinChange(coins, amount);
        var memoResult = CoinChange.getAlgorithm("MEMO").coinChange(coins, amount);

        assertEquals(expected, dpResult);
        assertEquals(dpResult, memoResult);
    }

}