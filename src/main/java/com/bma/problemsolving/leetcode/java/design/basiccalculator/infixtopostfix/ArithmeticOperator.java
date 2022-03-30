package com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix;

public interface ArithmeticOperator extends Operator {
    Integer eval(Operand<Integer> a, Operand<Integer> b);
}
