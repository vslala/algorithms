package com.bma.problemsolving.leetcode.java;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        for (String str : strs) {
            String sortedStr = sortStr(str.toCharArray());
            if (group.containsKey(sortedStr)) group.get(sortedStr).add(str);
            else group.put(sortedStr, new LinkedList<>() {
                {
                    add(str);
                }
            });
        }
        return new ArrayList<>(group.values());
    }

    int sumChars(char[] strChars) {
        int sum = 0;
        for (int i = 0; i < strChars.length; i++) {
            sum += strChars[i];
        }
        return sum;
    }

    String sortStr(char[] strChars) {
        Arrays.sort(strChars);
        return new String(strChars);
    }
}
