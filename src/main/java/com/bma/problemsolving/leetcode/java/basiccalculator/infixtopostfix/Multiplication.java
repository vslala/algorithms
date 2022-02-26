package com.bma.problemsolving.leetcode.java.basiccalculator.infixtopostfix;

class Multiplication implements ArithmeticOperator {

    @Override
    public String association() {
        return null;
    }

    @Override
    public Integer precedence() {
        return 2;
    }

    @Override
    public Integer eval(Operand<Integer> a, Operand<Integer> b) {
        return a.get() * b.get();
    }

    @Override
    public String toString() {
        return "*";
    }
}
