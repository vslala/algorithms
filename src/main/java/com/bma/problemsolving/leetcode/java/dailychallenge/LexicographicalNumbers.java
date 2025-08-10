package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 08/06/2025
 */
class LexicographicalNumbers {
    /**
     * Iterative method using stack to build give the next lexicographical number
     * in the sequence
     */
    private static class LexicographicalIterator implements Iterator<Integer> {
        private final int limit;
        private Integer nextVal = null;
        private final Stack<Integer> stack;
        public LexicographicalIterator(int limit) {
            this.limit = limit;
            stack = new Stack<>();

            for (int i = 9; i >= 1; i--) {
                stack.push(i);
            }
            advance();
        }

        private void advance() {
            this.nextVal = null;
            while (!stack.isEmpty()) {
                int curr = stack.pop();
                if (curr > limit) continue;
                this.nextVal = curr;

                for (int nextDigit = 9; nextDigit >= 0; nextDigit--) {
                    int nextNum = curr * 10 + nextDigit;
                    if (nextNum <= limit) {
                        stack.push(nextNum);
                    }
                }
                break;
            }
        }

        @Override
        public boolean hasNext() {
            return nextVal != null;
        }

        @Override
        public Integer next() {
            if (nextVal == null) throw new NoSuchElementException();
            int result = nextVal;
            advance();
            return result;
        }
    }

    public List<Integer> lexicalOrder(int n) {
        var itr = new LexicographicalIterator(n);
        var result = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            result.add(itr.next());
        }

        return result;
    }

    /**
     * Recursive DFS approach to build the list up until the limit
     * @param curr curr prefix
     * @param limit limit till the sequence needs to be generated
     * @param result data structure to hold the sequence
     */
    private void dfs(int curr, int limit, List<Integer> result) {
        if (curr > limit) return;
        result.add(curr);
        for (int nextDigit = 0; nextDigit <= 9; nextDigit ++) {
            int nextNumber = curr * 10 + nextDigit;
            if (nextDigit <= limit) {
                dfs(nextNumber, limit, result);
            } else {
                break;
            }
        }
    }
}
