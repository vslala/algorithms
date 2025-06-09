package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 16/05/2025
 */
class CoinChangeTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[1,2,5] 11 3",
            "[1,2,5] 3 2",
            "[2] 3 -1",
            "[1,2,5] 7 2",
            "[1] 0 0",
            "[1] 1 1"
    }, delimiter = ' ')
    void shouldReturnTheMinCoinsRequiredToReachTheTarget(String coinsStr, int amount, int expected) {
        int[] coins = Fixtures.parse1DArray(coinsStr);

        var sol = new CoinChange();
        int output = sol.coinChange(coins, amount);

        assertEquals(expected, output);
    }
}