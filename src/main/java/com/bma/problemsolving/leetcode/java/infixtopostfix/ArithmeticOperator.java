package com.bma.problemsolving.leetcode.java.infixtopostfix;

public interface ArithmeticOperator extends Operator {
    default <T extends Comparable<T>> T eval(Operand<T> a, Operand<T> b) {
        throw new RuntimeException("No implementation found for this operation!!!");
    }
}
