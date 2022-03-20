package com.bma.problemsolving.leetcode.java.dfsbfs;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 * <p>
 * Return all the possible results. You may return the answer in any order.
 *
 * @author varun.shrivastava
 */
class RemoveInvalidParenthesis {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        // guard clause for input validation
        if (s == null) return result;

        Set<String> visited = new HashSet<>();
        Deque<String> queue = new LinkedList<>();

        // initialize
        queue.offer(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            String expr = queue.poll();

            if (isValid(expr)) {
                result.add(expr);
                found = true;
            }

            // if we have found the valid expression then no need to perform any operation
            // since this is a BFS, we will always find the solution with minimum deletions
            // then all we have to do is check the rest of the expressions that are present in the queue
            if (found) continue;

            // this code only runs for the invalid expression, that is when we want to generate all combinations by deleting one parenthesis each time
            // for ex: )(f
            // then this code will generate: (f, )f, f
            for (int i = 0; i < expr.length(); i++) {
                // we only try to remove left or right parenthesis
                if (expr.charAt(i) != '(' && expr.charAt(i) != ')') continue;

                String t = expr.substring(0, i) + expr.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it'expr not visited, add it to the queue
                    queue.offer(t);
                    visited.add(t);
                }
            }
        }

        return result;
    }

    /**
     * check whether the given expression is valid or not by comparing the count of '(' and ')'
     * @param expr with or without parenthesis
     * @return true if valid false otherwise
     */
    private boolean isValid(String expr) {
        int count = 0;
        for (char c: expr.toCharArray()) {
            if (c == '(') count++;
            if (c == ')') count--;

            // pruning the search: if at any point of time we found more closing brackets than the opening brackets,
            // it means that expr can never be valid, so no need to iterate till the end
            if (count < 0) return false;
        }

        return count == 0;
    }
}
