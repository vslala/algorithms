package com.bma.problemsolving.leetcode.java.infixtopostfix;

import java.util.*;

public class ReversePolishNotation {

    public Integer eval(final String[] notation) {
        var arithmeticOperations = Map.of(
                "/", new Division(),
                "*", new Multiplication(),
                "+", new Addition(),
                "-", new Subtraction()
        );

        var operands = new LinkedList<Operand<Integer>>();

        Arrays.stream(notation).forEach(token -> {
            if (isANumber(token)) operands.push(new IntegerOperand(token));
            else {
                var b = operands.pop();
                var a = operands.pop();
                operands.push(new IntegerOperand(arithmeticOperations.get(token).eval(a, b)));
            }
        });

        return operands.pop().get();
    }

    private boolean isANumber(String token) {
        if (token.equals("-")) return false;
        return token.chars().filter(c -> c  != '-').allMatch(Character::isDigit);
    }
}
