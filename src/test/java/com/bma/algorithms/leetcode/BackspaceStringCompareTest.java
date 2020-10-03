package com.bma.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackspaceStringCompareTest {

    private BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();

    @Test
    public void compareBothString() {
        String s1 = "ab##c", s2 = "ad##c";
        boolean flag = backspaceStringCompare.backspaceCompare(s1, s2);
        assertEquals(true, flag);

        s1 = "y#fo##f"; s2="y#f#o##f";
        flag = backspaceStringCompare.backspaceCompare(s1, s2);
        assertEquals(true, flag);
    }
}