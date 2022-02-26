package com.bma.problemsolving.leetcode.java.basiccalculator.infixtopostfix;

public interface ArithmeticOperator extends Operator {
    Integer eval(Operand<Integer> a, Operand<Integer> b);
}
