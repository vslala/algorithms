package com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix;

interface Operator {
    String association();
    Integer precedence();
}
