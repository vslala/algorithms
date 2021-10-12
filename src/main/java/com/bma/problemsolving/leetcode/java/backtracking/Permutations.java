package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 46. Permutations
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * @author varun.shrivastava
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        var result = new ArrayList<List<Integer>>();
        var currIndex = 0;

//        var arr = Arrays.stream(nums).boxed().collect(Collectors.toList());
//        findAll(arr, currIndex, result);
        findAll(nums, new HashSet<Integer>(), result);

        return result;
    }

    // this is using list
    private void findAll(int[] nums, HashSet<Integer> set, ArrayList<List<Integer>> result) {
        if (set.size() == nums.length) {
            result.add(new ArrayList<>(set));
            return;
        }

        for (var i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                findAll(nums, set, result);
                set.remove(nums[i]);
            }
        }
    }

    // this is using swap array
    void findAll(List<Integer> nums, int currIndex, List<List<Integer>> result) {
        if (currIndex == nums.size()) {
            result.add(new ArrayList<>(nums));
            return;
        }

        for (var index = currIndex; index < nums.size(); index++) {
            swap(nums, index, currIndex);
            findAll(nums, currIndex + 1, result);
            swap(nums, currIndex, index);
        }
    }

    void swap(List<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }
}
