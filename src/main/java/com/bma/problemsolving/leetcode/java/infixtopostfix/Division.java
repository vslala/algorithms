package com.bma.problemsolving.leetcode.java.infixtopostfix;

class Division implements ArithmeticOperator {

    @Override
    public String association() {
        return null;
    }

    @Override
    public Integer getPrecedence() {
        return 2;
    }

    @Override
    public Integer eval(Operand a, Operand b) {
        return 1;
    }

    @Override
    public String toString() {
        return "/";
    }
}
