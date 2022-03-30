package com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix;

interface Operand<T extends Comparable<T>> {
    T get();
}
