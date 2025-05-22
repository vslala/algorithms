package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 22/05/2025
 */
class ZeroArrayTransformationsPartThree {
    /**
     * The core principle is to compute the operation per index
     * We need to find the smallest query start and the largest end that starts from the given index `i` (for the same index)
     * and increment the operations on that index and store the negative operations count at the end of the query index
     * so that it normalized on each iteration. Ref: {@link ZeroArrayTransformationPartOne}
     * Then we check if our operations count is less than that index then it means it can't be done and return -1
     * else go on and do the same with next index.
     * @param nums
     * @param queries
     * @return
     */
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, Comparator.comparingInt(q -> q[0]));
        int[] delta = new int[nums.length + 1];
        var maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        int operations = 0;

        for (int i = 0, j = 0; i < nums.length; i++) {
            operations += delta[i];

            while (j < queries.length && queries[j][0] == i) {
                maxHeap.offer(queries[j][1]);
                j++;
            }

            while (operations < nums[i] && !maxHeap.isEmpty() && maxHeap.peek() >= i) {
                operations += 1;
                delta[maxHeap.poll() + 1] -= 1;
            }

            if (operations < nums[i]) {
                return -1;
            }
        }

        return maxHeap.size();
    }
}
