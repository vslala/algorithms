package com.bma.problemsolving.leetcode.java.infixtopostfix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InfixToPostfixConverterTest {

    private ExpressionParser expressionParser;

    @BeforeEach
    void setup() {
        expressionParser = new ExpressionParser();
    }

    @Test
    void itShouldConvertInfixExpressionToPostFixExpression() {
        var expressions = Map.of(
                " 2-1 + 2 ", "2 1 - 2 +",
                "1 + 1", "1 1 +",
                "(1+(4+5+2)-3)+(6+8)", "1 4 5 + 2 + + 3 - 6 8 + +"
        );

        var infixToPostfix = new InfixToPostfixConverter(expressionParser);

        expressions.forEach((key,val) -> assertEquals(val, infixToPostfix.convert(key)));
    }

}