package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.List;

class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        var result = new ArrayList<List<Integer>>();

        backtrack(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] candidates, int target, int start, ArrayList<Integer> curr, ArrayList<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            curr.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, curr, result);
            curr.removeLast();
        }
    }
}
