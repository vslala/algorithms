package com.bma.problemsolving.leetcode.java.basiccalculator.infixtopostfix;

import com.bma.problemsolving.leetcode.java.basiccalculator.infixtopostfix.ExpressionParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionParserTest {

    @Test
    void itShouldParseTheInfixExpressionIntoTokens() {
        var expressions = Map.of(
                " 2-1 + 2 ", List.of("2", "-", "1", "+", "2"),
                "1 + 1", List.of("1", "+", "1"),
                "(1+(4+5+2)-3)+(6+8)", List.of("(", "1", "+", "(", "4", "+", "5", "+", "2", ")", "-", "3", ")", "+", "(", "6", "+", "8", ")")
        );
        var infixParser = new ExpressionParser();

        expressions.forEach((expression, expected) ->
                assertEquals(expected, infixParser.parseInfix(expression)));
    }
}