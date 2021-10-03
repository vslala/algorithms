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
}
