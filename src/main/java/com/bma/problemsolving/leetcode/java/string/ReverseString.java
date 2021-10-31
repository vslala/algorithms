package com.bma.problemsolving.leetcode.java.string;

/**
 * 344. Reverse String
 * @author varun.shrivastava
 */
public class ReverseString {
    public void reverseString(char[] s) {
        for (int left = 0, right = s.length - 1; left < right; left++, right--) {
            var tmp = s[right];
            s[right] = s[left];
            s[left] = tmp;
        }
    }

    public void reverseStringRecursive(char[] s) {
        reverseString(s, 0);
    }

    private void reverseString(char[] s, int index) {
        if (index == s.length) {
            return;
        }

        var c = s[index];
        reverseString(s, index + 1);
        s[s.length - index - 1] = c;
    }
}
