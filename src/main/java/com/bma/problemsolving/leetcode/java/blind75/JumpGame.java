package com.bma.problemsolving.leetcode.java.blind75;

class JumpGame {
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;

        for (int i = nums.length - 2; i >= 0; i--) {
            int farthestJump = Math.min(nums[i] + i, nums.length - 1);
            for (int j = i + 1; j <= farthestJump; j++) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0];
    }
}
