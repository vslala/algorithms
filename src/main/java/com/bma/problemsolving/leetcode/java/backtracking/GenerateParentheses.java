package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 22/05/2025
 */
class GenerateParentheses {
    private List<String> combinations;
    public List<String> generateParenthesis(int n) {
        this.combinations = new ArrayList<>();
        if (n == 0) {
            return combinations;
        }

        backtrack(new StringBuilder(), 0, 0, n);
        return combinations;
    }

    private void backtrack(StringBuilder sb, int left, int right, int n) {
        if (sb.length() == 2 * n) {
            this.combinations.add(sb.toString());
            return;
        }

        if (left < n) {
            sb.append("(");
            backtrack(sb, left + 1, right, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (left > right) {
            sb.append(")");
            backtrack(sb, left, right + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
