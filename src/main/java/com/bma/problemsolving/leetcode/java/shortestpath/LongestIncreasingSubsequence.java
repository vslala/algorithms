package com.bma.problemsolving.leetcode.java.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class LongestIncreasingSubsequence {

    private final int totalVertices;
    private AtomicInteger longest;
    private Map<Integer, List<Integer>> graph;

    public LongestIncreasingSubsequence(int[] nums) {
        this.totalVertices = nums.length;
        graph = new HashMap<>();

        // init empty graph
        for (int i = 0; i < totalVertices; i++) {
            graph.put(i, new ArrayList<>());
        }

        // construct a graph from left to right of all connecting vertices
        int curr;
        for (int i = 0; i < totalVertices; i++) {
            curr = nums[i];
            for (int j = i + 1; j < totalVertices; j++) {
                if (nums[j] > curr) {
                    graph.get(i).add(j);
                }
            }
        }
    }

    public int longestLIS() {
        longest = new AtomicInteger(0);
        for (int v = 0; v < totalVertices; v++) {
            dfs(v, 1);
        }

        return longest.intValue();
    }

    private void dfs(int v, int weight) {
        if (weight > longest.intValue())
            longest.set(weight);

        for (int w : graph.get(v)) {
            dfs(w, weight + 1);
        }
    }
}
