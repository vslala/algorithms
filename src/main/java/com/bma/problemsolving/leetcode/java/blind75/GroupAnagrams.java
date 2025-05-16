package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return List.of(List.of());

        var dict = new HashMap<String, List<String>>();

        for (String word: strs) {
            char[] temp = word.toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);
            var list = dict.getOrDefault(key, new ArrayList<>());
            list.add(word);
            dict.put(key, list);
        }

        return new ArrayList<>(dict.values());
    }
}
