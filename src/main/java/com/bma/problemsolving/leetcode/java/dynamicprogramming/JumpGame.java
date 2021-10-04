package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.problemsolving.Timer;
import lombok.extern.slf4j.Slf4j;

/**
 * 55. Jump Game
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 *
 * @author varun.shrivastava
 */
@Slf4j
class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;

        var jumpBoard = nums;
        var pos = 0;
        var canJump = new boolean[1];

//        log.info("Total Execution Time: {}ms", Timer.timeIt(() -> usingDFSWithDynamicProgramming(jumpBoard, pos, canJump)));
        log.info("Total Execution Time: {}ms", Timer.timeIt(() -> usingGreedyKadaneAlgorithm(jumpBoard, canJump)));

        return canJump[0];
    }

    private void usingGreedyKadaneAlgorithm(int[] jumpBoard, boolean[] canJump) {
        var reach = 0;
        for (var i = 0; i < jumpBoard.length; i++) {
            reach = Math.max(reach, i + jumpBoard[i]);
            if (reach == i) {
                return;
            }

            if (reach >= jumpBoard.length - 1) {
                canJump[0] = true;
                return;
            }
        }

        canJump[0] = true;
    }

    private void usingDFSWithDynamicProgramming(int[] jumpBoard, int pos, boolean[] canJump) {
        var jumpRange = jumpBoard[pos];

        var start = pos;
        while (start < pos + jumpRange) {
            start += 1;
            if (start >= jumpBoard.length - 1) {
                canJump[0] = true;
                return;
            }
            usingDFSWithDynamicProgramming(jumpBoard, start, canJump);
            jumpBoard[pos] = 0;
        }
    }
}
