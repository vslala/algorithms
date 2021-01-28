package com.bma.problemsolving.leetcode.java.infixtopostfix;

public class OpenParenthesis implements GroupingOperator {
    @Override
    public String association() {
        return null;
    }

    @Override
    public Integer precedence() {
        return -1;
    }

    @Override
    public String toString() {
        return "(";
    }
}
