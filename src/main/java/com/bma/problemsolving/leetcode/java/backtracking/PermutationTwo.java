package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 47. Permutations II
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * @author varun.shrivastava
 */
class PermutationTwo {
    private List<List<Integer>> result = new ArrayList<>();
    private HashSet<List<Integer>> set = new HashSet<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> combinations = new ArrayList<>();
        var aux = new int[nums.length];
        findAll(nums, aux, combinations);
        result.addAll(set);

        return result;
    }

    public void findAll(int[] nums, int[] aux, List<Integer> combinations) {
        if (nums.length == combinations.size()) {
            set.add(new ArrayList<>(combinations));
            return;
        }
        for (var i = 0; i < nums.length; i++) {
            if (aux[i] == 0) {
                aux[i] = 1;
                int idx = combinations.size();
                combinations.add(nums[i]);
                findAll(nums, aux, combinations);
                combinations.remove(idx);
                aux[i] = 0;
            }
        }
    }
}
