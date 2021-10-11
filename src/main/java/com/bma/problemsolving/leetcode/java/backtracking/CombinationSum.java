package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. Combination Sum
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 * @author varun.shrivastava
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        var result = new ArrayList<List<Integer>>();
        var currIndex = 0;
        findUniqueCombinations(candidates, target, currIndex, new ArrayList<>(), result);

        return result;
    }

    private void findUniqueCombinations(int[] candidates, int target, int currIndex, ArrayList<Integer> currSet, ArrayList<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            // adding new list because if same list reference is used
            // which is used through out the recursion will end up empty at the end
            result.add(new ArrayList<>(currSet));
            return;
        }
        if (currIndex == candidates.length && target - candidates[currIndex - 1] != 0) {    // target check here is for a situation where there is only one element present in the candidates array
            return;
        }

        // starting iteration from the curr point because every combination before currIndex has been evaluated.
        // this would remove the repetitive sets such as [[2,2,3], [3,2,2], [2,3,2]].
        for (var i = currIndex; i < candidates.length; i++) {
            currSet.add(candidates[i]);
            findUniqueCombinations(candidates, target - candidates[i], i, currSet, result);
            currSet.remove(currSet.size() - 1);
        }

    }
}
