package com.bma.problemsolving.leetcode.java.basiccalculator;

import com.bma.problemsolving.leetcode.java.infixtopostfix.ExpressionParser;
import com.bma.problemsolving.leetcode.java.infixtopostfix.InfixToPostfixConverter;
import com.bma.problemsolving.leetcode.java.infixtopostfix.ReversePolishNotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicCalculatorTest {

    @Test
    void itShouldEvaluateTheGivenExpressionAndReturnTheResult() {
        String infixExpression = "(1+(4+5+2)-3)+(6+8)";
        var expressionParser  = new ExpressionParser();
        var infixToPostfixConverter = new InfixToPostfixConverter(expressionParser);
        var reversePolishNotation = new ReversePolishNotation();

        var calculator = new BasicCalculator(infixToPostfixConverter, reversePolishNotation);
        int result = calculator.calculate(infixExpression);
        assertEquals(23, result);
    }
}