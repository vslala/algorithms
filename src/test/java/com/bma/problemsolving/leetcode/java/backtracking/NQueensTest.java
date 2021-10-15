package com.bma.problemsolving.leetcode.java.backtracking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NQueensTest {
    private NQueens sol = new NQueens();

    @Test
    void shouldReturnAllPossibleConfigurationsForGivenQueen() {
        var expected = List.of(List.of(".Q..", "...Q", "Q...", "..Q."), List.of("..Q.", "Q...", "...Q", ".Q.."));
        var n = 4;
        var result = sol.solveNQueens(n);

        assertEquals(expected, result);
    }
}
