package com.bma.problemsolving.leetcode.java.design.basiccalculator;

import com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix.InfixToPostfixConverter;
import com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix.ReversePolishNotation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicCalculator {
    private final InfixToPostfixConverter infixToPostfixConverter;
    private final ReversePolishNotation reversePolishNotation;

    public int calculate(String infixExpression) {
        String postfix = infixToPostfixConverter.convert(infixExpression);
        return reversePolishNotation.eval(postfix.split(" "));
    }
}
