package com.bma.problemsolving.leetcode.java.basiccalculator.infixtopostfix;

interface Operand<T extends Comparable<T>> {
    T get();
}
