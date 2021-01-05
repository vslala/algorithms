package com.bma.problemsolving.leetcode.java.infixtopostfix;

interface Operator {
    String association();
    Integer getPrecedence();
    Integer eval(Operand a, Operand b);
}
