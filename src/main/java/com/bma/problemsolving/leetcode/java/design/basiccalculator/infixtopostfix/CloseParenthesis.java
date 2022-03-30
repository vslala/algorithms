package com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix;

public class CloseParenthesis implements GroupingOperator {
    @Override
    public String association() {
        return "l2r";
    }

    @Override
    public Integer precedence() {
        return 3;
    }

    @Override
    public String toString() {
        return ")";
    }
}
