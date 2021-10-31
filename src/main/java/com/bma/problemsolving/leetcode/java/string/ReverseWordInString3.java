package com.bma.problemsolving.leetcode.java.string;

/**
 * 557. Reverse Words in a String III
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 * @author varun.shrivastava
 */
public class ReverseWordInString3 {
    public String reverseWords(String s) {
        var result = s.toCharArray();

        var right = 0;
        var left = 0;

        for (int index = 0; index <= result.length; index++) {
            if (index == result.length || result[index] == ' ') {
                right = index - 1;
                for (;left < right; left++, right--) {
                    var temp = result[left];
                    result[left] = result[right];
                    result[right] = temp;
                }

                left = index + 1;
            }
        }

        return String.valueOf(result);
    }
}
