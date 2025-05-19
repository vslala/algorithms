package com.bma.problemsolving.leetcode.java.string;

import java.util.ArrayList;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 18/05/2025
 */
class StringMatching {

    public int strStr(String haystack, String needle) {
        var startPos = new ArrayList<Integer>();

        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)
                    && haystack.length() - i >= needle.length()) {
                startPos.add(i);
            }

        }

        for (int pos: startPos) {
            boolean found = true;
            for (int i = pos, j = 0; j < needle.length(); i++, j++) {
                if (haystack.charAt(i) != needle.charAt(j)) {
                    found = false;
                    break;
                }
            }

            if (found) return pos;
        }

        return -1;
    }
}
