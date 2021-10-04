package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateParenthesesTest {

    private GenerateParentheses sol = new GenerateParentheses();

    @ParameterizedTest
    @CsvSource({
            "3, 5"
    })
    void shouldGenerateAllValidParenthesis(int input, int expectedCombinations) {
        List<String> combinations = sol.generateParenthesis(input);
        assertEquals(expectedCombinations, combinations.size());
    }

}