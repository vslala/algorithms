package com.bma.problemsolving.leetcode.java.dfsbfs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveInvalidParenthesisTest {

    private RemoveInvalidParenthesis sol = new RemoveInvalidParenthesis();

    @ParameterizedTest
    @CsvSource(value = {
            "()())() (())()_()()()",
            ")()( ()",
            "(a)())() (a())()_(a)()()",
            ")(f f",
    }, delimiter = ' ')
    void shouldGenerateAllCombinationsOfValidParenthesis(String input, String outputExpr) {
        List<String> expected = List.of(outputExpr.split("_"));
        List<String> output = sol.removeInvalidParentheses(input);

        System.out.println(output);

        assertTrue(expected.containsAll(output));
    }
}