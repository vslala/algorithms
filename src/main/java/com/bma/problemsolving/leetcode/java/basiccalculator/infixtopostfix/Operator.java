package com.bma.problemsolving.leetcode.java.basiccalculator.infixtopostfix;

interface Operator {
    String association();
    Integer precedence();
}
