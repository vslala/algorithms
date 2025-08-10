package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.ArrayList;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 02/06/2025
 */
class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        var leftToRight = new ArrayList<Integer>();
        var rightToLeft = new ArrayList<Integer>();

        for (int rating: ratings) {
            leftToRight.add(1);
            rightToLeft.add(1);
        }

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                leftToRight.set(i, leftToRight.get(i - 1) + 1);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rightToLeft.set(i, rightToLeft.get(i + 1) + 1);
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(leftToRight.get(i), rightToLeft.get(i));
        }

        return sum;
    }
}
