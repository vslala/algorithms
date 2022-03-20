package com.bma.problemsolving.meta.heap;

import java.util.PriorityQueue;

/**
 * You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that,
 * for each index i (between 0 and n-1, inclusive), output[i] is equal to the product of the three largest elements out of arr[0..i] (or equal to -1 if i < 2, as arr[0..i]then includes fewer than three elements).
 * Note that the three largest elements used to form any product may have the same values as one another, but they must be at different indices in arr.
 *
 * @author varun.shrivastava
 */
class LargestTripleProduct {

    int[] findMaxProduct(int[] arr) {
        int[] output = new int[arr.length];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < arr.length; i++) {
            maxHeap.offer(arr[i]);

            if (maxHeap.size() < 3) {
                output[i] = -1;
            } else {
                int x = maxHeap.poll();
                int y = maxHeap.poll();
                int z = maxHeap.poll();

                output[i] = x * y * z;

                maxHeap.offer(x);
                maxHeap.offer(y);
                maxHeap.offer(z);
            }
        }

        return output;
    }
}
