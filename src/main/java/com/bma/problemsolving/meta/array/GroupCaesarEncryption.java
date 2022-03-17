package com.bma.problemsolving.meta.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a list of string, group them if they are same after using Ceaser Cipher Encrpytion.
 * Definition of "same", "abc" can right shift 1, get "bcd", here you can shift as many time as you want, the string will be considered as same.
 *
 * Input: ["abc", "bcd", "acd", "dfg"]
 * Output: [["abc", "bcd"], ["acd", "dfg"]]
 *
 * @author varun.shrivastava
 */
class GroupCaesarEncryption {
    private Map<String, List<String>> map;

    /**
     * Time Complexity: O(n * k)
     *
     * Steps to group the caesar encryption:
     * 1. Calculate hash for each word using {@link #calculateHash(String, int)}
     * 2. Add it to the map
     *
     * @param input
     * @return
     */
    public List<List<String>> group(List<String> input) {
        map = new HashMap<>();
        for (String word: input) {// O(n)
            int shift = word.charAt(0); // O(1)
            String hash = calculateHash(word, shift); // O(k)
            map.computeIfAbsent(hash, x -> new ArrayList<>()).add(word); // O(1)
        }

        return new ArrayList<>(map.values());
    }

    private String calculateHash(String word, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c: word.toCharArray()) {
            int leftShift = c - shift + 26; // +26 is to prevent underflow, assume input as za so 'a' - 'z' would yield -26 so +26 is to make sure the value is always positive.
            sb.append(leftShift % 26 + 'a');
        }

        return sb.toString();
    }
}
