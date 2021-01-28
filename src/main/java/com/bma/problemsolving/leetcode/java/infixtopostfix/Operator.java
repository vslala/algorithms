package com.bma.problemsolving.leetcode.java.infixtopostfix;

interface Operator {
    String association();
    Integer precedence();
}
