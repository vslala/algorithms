package com.bma.problemsolving.leetcode.java.basiccalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicCalculatorTest {

    @Test
    void itShouldConvertTheGivenExpressionIntoAPostFixFormat() {
        String infixExpression = "(1+(4+5+2)-3)+(6+8)";
        var calculator = new BasicCalculator();
        int result = calculator.calculate(infixExpression);
        assertEquals(23, result);
    }
}