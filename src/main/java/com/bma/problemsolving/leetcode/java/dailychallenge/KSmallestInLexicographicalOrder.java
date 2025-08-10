package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 09/06/2025
 */
class KSmallestInLexicographicalOrder {

    public int findKthNumber(int n, int k) {
        int curr = 1;
        int remaining = k - 1;

        while (remaining > 0) {
            int steps = countSteps(n, curr, curr + 1);
            if (steps <= remaining) {
                curr += 1;
                remaining -= steps;
            } else {
                curr *= 10;
                remaining -= 1;
            }
        }

        return curr;
    }

    private int countSteps(int limit, long prefix1, long prefix2) {
        int steps = 0;
        while (prefix1 <= limit) {
            steps += (int) (Math.min(limit + 1, prefix2) - prefix1);
            prefix1 *= 10;
            prefix2 *= 10;
        }
        return steps;
    }
}
