package com.bma.problemsolving.leetcode.java.contest;

public class BiWeeklyContest53 {

    public int countGoodSubstrings(String s) {
        int count = 0;

        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) != s.charAt(i + 1)
                    && s.charAt(i) != s.charAt(i + 2)
                    && s.charAt(i + 1) != s.charAt(i + 2)
            ) {
                System.out.println(s.substring(i, i + 3));
                count++;
            }
        }

        return count;
    }

    public int minXORSum(int[] nums1, int[] nums2) {
        int min = Integer.MAX_VALUE;
        int k = 0;

        while (true) {
            int i = 0;
            int j = k;
            int sum = 0;

            while (i < nums1.length) {
                sum += nums1[i] ^ nums2[j % nums2.length];
                i++;
                j++;
            }

            if (sum < min) min = sum;

            k++;

            if (k == nums2.length)
                break;
        }

        return min;
    }

    public int maxRhombusSum(int[][] ints) {
        return 0;
    }
}
