package com.bma.problemsolving.leetcode.java;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReversePolishNotation {

    interface ArithmeticOperation {
        Optional<Integer> evaluate(Integer a, Integer b, String operand);
    }

    class Add implements ArithmeticOperation {

        @Override
        public Optional<Integer> evaluate(Integer a, Integer b, String operand) {
            if (operand.equals("+"))
                return Optional.of(a + b);

            return Optional.empty();
        }
    }

    class Subtract implements ArithmeticOperation {

        @Override
        public Optional<Integer> evaluate(Integer a, Integer b, String operand) {
            if (operand.equals("-"))
                return Optional.of(a - b);
            return Optional.empty();
        }
    }

    class Multiply implements ArithmeticOperation {
        @Override
        public Optional<Integer> evaluate(Integer a, Integer b, String operand) {
            if (operand.equals("*"))
                return Optional.of(a * b);
            return Optional.empty();
        }
    }

    class Divide implements ArithmeticOperation {

        @Override
        public Optional<Integer> evaluate(Integer a, Integer b, String operand) {
            if (operand.equals("/"))
                return Optional.of(a / b);
            return Optional.empty();
        }
    }

    public Integer eval(final String[] notation) {
        var operations = List.of(
                new Add(), new Subtract(), new Multiply(), new Divide()
        );

        var operands = new LinkedList<Integer>();

        Arrays.stream(notation).forEach(token -> {
            if (isANumber(token)) operands.push(Integer.parseInt(token));
            else {
                Integer b = operands.pop();
                Integer a = operands.pop();
                operations.forEach(operation -> operation.evaluate(a, b, token)
                        .ifPresent(operands::push));
            }
        });

        return operands.pop();
    }

    private boolean isANumber(String token) {
        if (token.equals("-")) return false;
        return token.chars().filter(c -> c  != '-').allMatch(Character::isDigit);
    }
}
