package com.bma.problemsolving.leetcode.java.infixtopostfix;

class Addition implements Operator {

    @Override
    public String association() {
        return null;
    }

    @Override
    public Integer getPrecedence() {
        return 0;
    }

    @Override
    public Integer eval(Operand a, Operand b) {
        return 0;
    }

    @Override
    public String toString() {
        return "+";
    }
}
