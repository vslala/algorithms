package com.bma.problemsolving.leetcode.java;

import java.util.ArrayList;

public class StringRotate {

    public boolean bruteForce(String first, String second) {
        if (first.length() != second.length()) return false;

        for (int i = 0; i < first.length(); i++) {
            if (!first.equals(second)) {
                char a = first.charAt(0);
                String remaining = first.substring(1);
                first = remaining + a;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean rotateString(String first, String second) {
        // check for the length of the string
        // if the lengths are different then  return false
        if (first.length() != second.length()) return false;

        //  check if the input strings are equal and return true
        if (first.equals(second)) return true;

        /*
        Search for all the positions of first char of A in B.
        Iterate over each char of first string and start matching the second string char-by-char.
        If all the chars match then return true else check for the different starting position.
         */
        var startingCharsPosition = new ArrayList<Integer>();
        for (int i = 0; i < second.length(); i++)
            if (second.charAt(i) == first.charAt(0))
                startingCharsPosition.add(i);


        for (Integer pos : startingCharsPosition) {
            boolean isEqual = true;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt((pos + i) % first.length())) {
                    isEqual = false;
                    break;
                }
            }

            if (isEqual)
                return true;
        }

        return false;
    }
}
