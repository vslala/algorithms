package com.bma.problemsolving.leetcode.java.infixtopostfix;

import java.util.*;

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
        var arithmeticOperations = Map.of(
                "/", new Division(),
                "*", new Multiplication(),
                "+", new Addition(),
                "-", new Subtraction()
        );
        var operations = List.of(
                new Add(), new Subtract(), new Multiply(), new Divide()
        );

//        var operands = new LinkedList<Integer>();
        var operands = new LinkedList<Operand<Integer>>();

        Arrays.stream(notation).forEach(token -> {
            if (isANumber(token)) operands.push(new IntegerOperand(token));
            else {
                var b = operands.pop();
                var a = operands.pop();
                operands.push(new IntegerOperand(arithmeticOperations.get(token).eval(a, b)));
//                operations.forEach(operation -> operation.evaluate(a, b, token)
//                        .ifPresent(operands::push));
            }
        });

        return operands.pop().get();
    }

    private boolean isANumber(String token) {
        if (token.equals("-")) return false;
        return token.chars().filter(c -> c  != '-').allMatch(Character::isDigit);
    }
}
