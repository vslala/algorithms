package com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix;

final class IntegerOperand implements Operand<Integer> {

    private final Integer value;

    public IntegerOperand(Integer value) {
        this.value = value;
    }

    public IntegerOperand(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public Integer get() {
        return value;
    }
}
