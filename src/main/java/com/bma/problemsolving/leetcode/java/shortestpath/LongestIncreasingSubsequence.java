package com.bma.problemsolving.leetcode.java.shortestpath;

import com.bma.algorithms.sort.elementary.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class LongestIncreasingSubsequence {

    enum Algorithm {
        DYNAMIC_PROGRAMMING,
        MEMOIZATION,
        DEPTH_FIRST_SEARCH
    }

    interface LongestIncreasingSubsequenceInterface {
        public int longestLIS(int[] nums);
    }

    public static LongestIncreasingSubsequenceInterface get(Algorithm algorithm) {
        if (algorithm == Algorithm.DYNAMIC_PROGRAMMING) {
            return new DPSolution();
        }

        return new DFSSolution();
    }

    private static class DPSolution implements LongestIncreasingSubsequenceInterface {

        @Override
        public int longestLIS(int[] nums) {
            int best = 1; // every item in array is a increasing subsequence of length 1
            int n = nums.length;
            int[] length = new int[n];

            for (int k = 0; k < n; k++) {
                length[k] = 1;
                for (int i = 0; i < k; i++) {
                    if (nums[i] < nums[k]) {
                        length[k] = Math.max(length[k], length[i] + 1);
                        best = Math.max(best, length[k]);
                    }
                }
            }

            Util.println(length);
            return best;
        }
    }


    private static class DFSSolution implements LongestIncreasingSubsequenceInterface {
        private int totalVertices;
        private AtomicInteger longest;
        private Map<Integer, List<Integer>> graph;

        public DFSSolution() {}

        private void init(int[] nums) {
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

        public int longestLIS(int[] nums) {
            init(nums);
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
}
