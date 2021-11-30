package com.bma.problemsolving.leetcode.java.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinCollectionTest {

    private CoinCollection sol = new CoinCollection();

    // Beautiful Sequence: HHHHHTTT (All Heads followed by all tails)
    @ParameterizedTest
    @CsvSource({
            "HHTHTT, 1",
            "THHHTH, 2",
            "HHHHHH, 0",
            "HHHHHT, 0",
            "TTTTTH, 1"
    })
    void shouldReturnMinimumNumberOfCoinsToBeFlippedToObtainBeautifulSequence(String coinCollection, int expected) {
        assertEquals(expected, sol.minCoinFlips(coinCollection));
    }

}