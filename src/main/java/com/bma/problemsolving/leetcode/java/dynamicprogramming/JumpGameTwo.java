package com.bma.problemsolving.leetcode.java.dynamicprogramming;

/**
 * 45. Jump Game 2
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 *
 * @author varun.shrivastava
 */
public class JumpGameTwo {
    public int jump(int[] nums) {
        if (nums[0] == 0 || nums.length < 2) return 0;

        var totalJumps = new int[1];    // arrays are stored in heap unlike primitive that are stored in stack memory
        var startPosition = new Position(0, nums[0]);
        calculateNextBestPosition(nums, startPosition, totalJumps);

        return totalJumps[0];
    }

    private void calculateNextBestPosition(int[] nums, Position curr, int[] totalJumps) {
        totalJumps[0]++;
        if (curr.farthestJump() >= nums.length - 1) {
            return;
        }

        var boundary = new Boundary(curr.nextAdjacentIndex(), curr.farthestJump());
        var bestNextPos = new Position(0, -1);
        for (var walk = boundary.start; walk <= boundary.end; walk++) {
            var newPosition = new Position(walk, nums[walk]);
            if (newPosition.distance(nums) < bestNextPos.distance(nums)) {
                bestNextPos = newPosition;
            }
        }
        calculateNextBestPosition(nums, bestNextPos, totalJumps);

    }

    private class Boundary {
        int start;
        int end;

        public Boundary(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private class Position {
        int index;
        int jumpRange;

        public Position(int index, int jumpRange) {
            this.index = index;
            this.jumpRange = jumpRange;
        }

        int farthestJump() {
            return index + jumpRange;
        }

        int distance(int[] nums) {
            return nums.length - 1 - index - jumpRange;
        }

        public int nextAdjacentIndex() {
            return index + 1;
        }
    }
}
