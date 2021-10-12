package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. Combination Sum II
 * <p>
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * @author varun.shrivastava
 */
class CombinationSumTwo {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        var result = new ArrayList<List<Integer>>();
        var currSet = new LinkedList<Integer>();
        var currIndex = 0;

        // sorting array to remove the duplicate in the recursion
        // if prev == curr then skip.
        Arrays.sort(candidates);
        findUniqueCombinations(candidates, target, currIndex, currSet, result);

        return result;
    }

    private void findUniqueCombinations(int[] candidates, int target, int currIndex, LinkedList<Integer> currSet, ArrayList<List<Integer>> result) {
        if (target < 0)
            return;
        if (target == 0) {
            result.add(new ArrayList<>(currSet));
            return;
        }

        for (int index = currIndex; index < candidates.length; index++) {
            if (index == currIndex || candidates[index] != candidates[index - 1]) {
                currSet.add(candidates[index]);
                findUniqueCombinations(candidates, target - candidates[index], index + 1, currSet, result);
                currSet.remove(currSet.size() - 1);
            }
        }
    }
}
