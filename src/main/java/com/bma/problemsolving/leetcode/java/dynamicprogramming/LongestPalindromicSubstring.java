package com.bma.problemsolving.leetcode.java.dynamicprogramming;

/**
 * 5. Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * @author varun.shrivastava
 */
public class LongestPalindromicSubstring {

    private int resultStart = 0;
    private int resultLength = 0;

    public String longestPalindrome(String s) {
        for (var i = 0; i < s.length(); i++) {
            checkPalindromeByExpandingRange(s, i, i);
            checkPalindromeByExpandingRange(s, i, i + 1);
        }

        return s.substring(resultStart, resultStart + resultLength);
    }

    private void checkPalindromeByExpandingRange(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        var palindromeLength = end - start - 1;
        if (palindromeLength > resultLength) {
            resultLength = palindromeLength;
            resultStart = start + 1;
        }
    }

    public String longestPalindromeDP(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int gap = 0; gap < s.length(); gap++) {
            for (int i = 0, j = gap; j < dp.length; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = true;    // string of 1 char is a palindrome
                } else {
                    var isCharAtBothEndEqual = s.charAt(i) == s.charAt(j);
                    if (gap == 1) {
                        dp[i][j] = isCharAtBothEndEqual;  // if both char are same then the string is palindrome
                    } else {
                        dp[i][j] = isCharAtBothEndEqual && dp[i + 1][j - 1];
                    }
                }

                var palindromeLength = j - i + 1;
                if (dp[i][j] && palindromeLength > resultLength) {
                    resultStart = i;
                    resultLength = palindromeLength;
                }
            }
        }

        return s.substring(resultStart, resultStart + resultLength);
    }
}
