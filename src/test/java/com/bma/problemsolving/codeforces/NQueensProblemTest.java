package com.bma.problemsolving.codeforces;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NQueensProblemTest {

    @Test
    void sandbox() {

        // diagonal 1
        final int n = 4;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(i + j + ",");
            }
            System.out.println();
        }

        System.out.println();

        // diagonal 2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(i - j + n - 1 + ",");
            }
            System.out.println();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "4,2", "6,4"
    })
    void itShouldFindAllPossibleSolutions(int n, int expectedOutput) {
        var nQueensProblem = new NQueensProblem(n);
        int sols = nQueensProblem.findSolutions();
        assertEquals(expectedOutput, sols);
    }

}