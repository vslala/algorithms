package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordSearchTest {

    private WordSearch wordSearch = new WordSearch();

    @ParameterizedTest
    @CsvSource({
            "A_B_C_E:S_F_C_S:A_D_E_E, ABCCED, true",
            "A_B_C_E:S_F_C_S:A_D_E_E, SEE, true",
            "A_B_C_E:S_F_C_S:A_D_E_E, ABCB, false"

    })
    void shouldSearchTheWordAndReturnTrueOrFalseIfWordExistsInTheMatrixRespectively(String matrixStr, String word, boolean expected) {
        var board = Fixtures.createMatrix(matrixStr, "_", ":");
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + "|");
            }
            System.out.println();
        }
//        Util.printMatrix(board, "|");
        assertEquals(expected, wordSearch.exist(board, word));
    }
}