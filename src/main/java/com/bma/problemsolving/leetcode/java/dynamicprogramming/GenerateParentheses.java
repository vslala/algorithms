package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        var result = new ArrayList<String>();
//        var brackets = new char[2 * n];
//        var pos = 0;
//        generateAll(brackets, pos, result);
        var open = 0;
        var close = 0;
        backtrack(new StringBuilder(), open, close, n, result);

        return result;
    }

    private void backtrack(StringBuilder combination, int open, int close, int bracketPairs, ArrayList<String> result) {
        if (combination.length() == 2 * bracketPairs) {
            result.add(combination.toString());
            return;
        }

        if (open < bracketPairs) {
            combination.append('(');
            backtrack(combination, open + 1, close, bracketPairs, result);
            combination.deleteCharAt(combination.length() - 1); // backtrack
        }

        if (close < open) {
            combination.append(')');
            backtrack(combination, open, close + 1, bracketPairs, result);
            combination.deleteCharAt(combination.length() - 1); // backtrack
        }
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
        var count = 0;
        for (char bracket : brackets) {
            if (bracket == '(') count += 1;
            else count -= 1;

            if (count < 0) return false; // '(' should always come before
        }

        return count == 0;
    }
}
