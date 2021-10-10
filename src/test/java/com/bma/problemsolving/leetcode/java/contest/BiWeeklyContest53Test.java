package com.bma.problemsolving.leetcode.java.contest;

import org.junit.jupiter.api.Test;

class BiWeeklyContest53Test {

    @Test
    void goodSubstringTest() {
        var testClass = new BiWeeklyContest53();
        var output = testClass.countGoodSubstrings("aababcabc");
        System.out.println(output);
    }

    @Test
    void testMinimumXORSum() {
        var testClass = new BiWeeklyContest53();
        var output = testClass.minXORSum(new int[]{1, 2}, new int[]{2, 3});
        System.out.println(output);

        System.out.println(testClass.minXORSum(new int[]{1, 0, 3}, new int[]{5, 3, 4}));
    }

    @Test
    void biggestRhombus() {
        var testClass = new BiWeeklyContest53();
        var sum = testClass.maxRhombusSum(new int[][]{{3, 4, 5, 1, 3}, {3, 3, 4, 2, 3}, {20, 30, 200, 40, 10}, {1, 5, 5, 4, 1}, {4, 3, 2, 2, 5}});
        System.out.println(sum);
    }

}