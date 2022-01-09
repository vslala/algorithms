package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinChangeTwoTest {

    private CoinChangeTwo sol = new CoinChangeTwo();

    @ParameterizedTest
    @CsvSource({
            "5, 1_2_5, 4",
            "3, 2, 0",
            "10, 10, 1"
    })
    void shouldReturnTheNumberOfAllPossibleCombinationsUsingDenominatorThatSumsUpToGivenAmount(int amount, String coinsExpr, int expected) {
        int[] coins = Fixtures.splitAndParseArr(coinsExpr, "_");

        var result = sol.change(amount, coins);

        assertEquals(expected, result);
    }
}