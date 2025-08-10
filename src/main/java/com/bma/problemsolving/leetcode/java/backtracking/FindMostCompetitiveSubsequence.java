package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.algorithms.sort.elementary.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 19/05/2025
 */
class FindMostCompetitiveSubsequence {
    public int[] mostCompetitive(int[] nums, int k) {
        var mem = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        var ans = new ArrayList<Integer>(Integer.MAX_VALUE);
        var minSequence = new AtomicInteger();
        subsequence(mem, k, nums, 0, ans, minSequence);
        return toArray(ans);
    }

    private int[] toArray(ArrayList<Integer> ans) {
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    private void subsequence(List<Integer> mem, int k, int[] nums, int index, List<Integer> ans, AtomicInteger min) {

        if (mem.size() == k) {
            // process subsequence
            int num = parseNumber(mem);
            if (num < min.intValue()) {
                min.set(num);
                ans.clear();
                Util.println(mem);
                ans.addAll(new ArrayList<>(mem));
            }
            return;
        }

        if (index == nums.length) return;
        mem.add(nums[index]);
        subsequence(mem, k, nums, index + 1, ans, min);
        mem.removeLast();
        subsequence(mem, k, nums, index + 1, ans, min);
    }

    private int parseNumber(List<Integer> mem) {
        var sb = new StringBuilder();
        for (int num: mem) {
            sb. append(num);
        }

        return Integer.parseInt(sb.toString());
    }
}
