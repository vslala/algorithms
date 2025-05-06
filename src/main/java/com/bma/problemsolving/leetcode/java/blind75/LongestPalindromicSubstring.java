package com.bma.problemsolving.leetcode.java.blind75;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(i, i, s);
            int len2 = expand(i, i + 1, s);
            int len = Math.max(len1, len2);

            if (len > end - start) {
               start = i - ((len - 1) / 2);
               end   = i + (len / 2);
            }

        }

        return s.substring(start, end + 1);

    }

    private int expand(int i, int j, String s) {
        int left = i;
        int right = j;
        while (left > 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
