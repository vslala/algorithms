package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        char[] brackets = new char[2 * n];
        int pos = 0;
        var result = new ArrayList<String>();
        generateAll(brackets, pos, result);

        return result;
    }

    private void generateAll(char[] brackets, int pos, ArrayList<String> result) {
        if (pos == brackets.length) {
            if (isBalanced(brackets))
                result.add(new String(brackets));
            return;
        }

        brackets[pos] = '(';
        generateAll(brackets, pos + 1, result);

        brackets[pos] = ')';
        generateAll(brackets, pos + 1, result);
    }

    private boolean isBalanced(char[] brackets) {
        int count = 0;
        for (char bracket : brackets) {
            if (bracket == '(') count += 1;
            else count -= 1;

            if (count < 0) return false; // '(' should always come before
        }

        return count == 0;
    }
}
