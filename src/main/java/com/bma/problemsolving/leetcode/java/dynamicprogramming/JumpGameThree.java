package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1306. Jump Game III
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * Notice that you can not jump outside of the array at any time.
 * https://leetcode.com/problems/jump-game-iii/
 *
 * @author varun.shrivastava
 */
public class JumpGameThree {

    public boolean canReach(int[] arr, int start) {
        var graph = new HashMap<Integer, List<Integer>>();
        fillGraph(arr, start, graph);

        var allJumpIndices = graph.keySet();

        return allJumpIndices.stream()
                .anyMatch(index -> graph.get(index)
                        .stream()
                        .filter(jumpIndices -> isInBounds(arr, jumpIndices))
                        .anyMatch(inBoundIndex -> arr[inBoundIndex] == 0));
    }

    void fillGraph(int[] arr, int currIndex, Map<Integer, List<Integer>> graph) {
        if (graph.containsKey(currIndex)) return;

        if (isInBounds(arr, currIndex)) {
            var jumpRange = arr[currIndex];
            var toLeftJump = currIndex - jumpRange;
            var toRightJump = currIndex + jumpRange;

            graph.put(currIndex, List.of(toLeftJump, toRightJump));

            fillGraph(arr, toLeftJump, graph);
            fillGraph(arr, toRightJump, graph);
        }
    }

    private boolean isInBounds(int[] arr, int curr) {
        return curr >= 0 && curr < arr.length;
    }

}
