package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.algorithms.sort.elementary.Util;

class LongestCommonSubsequence {
    private String text1;
    private String text2;
    private int[][] mem;

    public int topDownApproach(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.mem = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                mem[i][j] = -1;
            }
        }

        return dp(0, 0);
    }

    private int dp(int p1, int p2) {
        if (mem[p1][p2] != -1){
            return mem[p1][p2];
        }

        int result = 0;
        if (text1.charAt(p1) == text2.charAt(p2)) {
            Util.println(String.format("Pick: [%d][%d] = {%s, %s} => %d", p1, p2, text1.charAt(p1), text2.charAt(p2), result));
            result = 1 + dp(p1 + 1, p2 + 1);
        } else {
            Util.println(String.format("Pick Either: [%d][%d] = {%s, %s} => %d", p1, p2, text1.charAt(p1), text2.charAt(p2), result));
            result = Math.max(dp(p1 + 1, p2), dp(p1, p2 + 1));
        }

        mem[p1][p2] = result;
        Util.println(String.format("Result: [%d][%d] = {%s, %s} => %d", p1, p2, text1.charAt(p1), text2.charAt(p2), result));

        return mem[p1][p2];
    }


    /**
     * Each subproblem is represented as a pair of indexes, and that there are text1.length() * text2.length() such possible subproblems,
     * we can iterate through the subproblems, starting from the smallest ones, and storing the answer for each.
     * When we get to the larger subproblems, the smaller ones that they depend on will already have been solved.
     * The best way to do this is to use a 2D array.
     *
     * @param text1
     * @param text2
     * @return
     */
    public int bottomUpApproach(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.mem = new int[text1.length() + 1][text2.length() + 1];

        for (int c = text2.length() - 1; c >= 0; c--) {
            for (int r = text1.length() - 1; r >= 0; r--) {
                if (text1.charAt(r) == text2.charAt(c)) {
                    mem[r][c] = 1 + mem[r + 1][c + 1];
                } else {
                    mem[r][c] = Math.max(mem[r + 1][c], mem[r][c + 1]);
                }
            }
        }

        return mem[0][0];
    }

}
