package com.bma.problemsolving.meta.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of n integers arr[0..(n-1)], determine the number of different pairs of elements within it which sum to k.
 * If an integer appears in the list multiple times, each copy is considered to be different; that is,
 * two pairs are considered different if one pair includes at least one array index which the other doesn't, even if they include the same values.
 *
 * @author varun.shrivastava
 */
class PairSums {
    /**
     * Steps to solve:
     * 1. Calculate the key that would sum up to the given number <(k - num)>
     * 2. If key is present, increment the counter + the value present for that key (number of times that number has repeated)
     * 3. Increment the count of the number that you have just seen
     *
     * @param arr given input array
     * @param k target sum
     * @return number of pairs that sums up to that target sum
     */
    int numberOfWays(int[] arr, int k) {
        Map<Integer, Integer> histogram = new HashMap<>();
        int count = 0;
        for (int num : arr) {
            int key = k - num;
            if (histogram.containsKey(key)) count += histogram.getOrDefault(key, 1);
            histogram.put(num, histogram.getOrDefault(num, 0) + 1);
        }

        return count;
    }
}
