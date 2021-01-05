package com.bma.problemsolving.leetcode.java.infixtopostfix;

final class IntegerOperand implements Operand<Integer> {

    private final Integer value;

    public IntegerOperand(Integer value) {
        this.value = value;
    }

    @Override
    public Integer get() {
        return value;
    }
}
