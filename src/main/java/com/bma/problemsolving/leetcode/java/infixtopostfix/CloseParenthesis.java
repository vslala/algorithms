package com.bma.problemsolving.leetcode.java.infixtopostfix;

public class CloseParenthesis implements GroupingOperator {
    @Override
    public String association() {
        return "l2r";
    }

    @Override
    public Integer getPrecedence() {
        return 3;
    }

    @Override
    public String toString() {
        return ")";
    }
}
