package com.bma.algorithms.leetcode;

import com.bma.algorithms.sort.elementary.Util;

import java.util.Stack;

public class BackspaceStringCompare {

    public boolean backspaceCompare(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        return finalStr(s1Chars).equals(finalStr(s2Chars));
    }

    private Stack finalStr(char[] s1Chars) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s1Chars.length; i++) {
            if (s1Chars[i] != '#') stack.push(s1Chars[i]);
            else if (!stack.isEmpty()) stack.pop();
        }
        Util.println(stack.toString());
        return stack;
    }
}
