package com.bma.problemsolving.leetcode.java.backtracking;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NQueensTwoTest {

    private NQueensTwo sol = new NQueensTwo();

    @ParameterizedTest
    @CsvSource({
            "4, 2",
            "1, 1",
            "5, 10"
    })
    void itShouldReturnNumberOfQueensThatCanBePlacedOnTheChessBoard(int input, int expectedOutput) {
        assertEquals(expectedOutput, sol.totalNQueens(input));
    }

}