package com.bma.problemsolving.leetcode.java.infixtopostfix;

public interface ArithmeticOperator extends Operator {
    Integer eval(Operand<Integer> a, Operand<Integer> b);
}
