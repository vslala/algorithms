package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.util.Collections.swap;

public class TopKFrequentElements {

    private final Random rand = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        // 1) build the frequency map
        var freqCounter = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freqCounter.put(num, freqCounter.getOrDefault(num, 0) + 1);
        }

        // 2) pull out the unique elements
        var keys = new ArrayList<>(freqCounter.keySet());

        // 3) quick-select so that the k largest freq’s are in the last k spots
        int n = keys.size();
        quickSelect(keys, 0, n - 1, n - k, freqCounter);

        // 4) collect the rightmost k elements
        int[] output = new int[k];
        for (int i = 0, j = n - 1; i < k; i++, j--) {
            output[i] = keys.get(j);
        }
        return output;
    }

    private void quickSelect(ArrayList<Integer> arr, int start, int end, int kIndex, HashMap<Integer, Integer> freq) {
        if (start >= end) return;
        // pick a random pivot in [start..end]
        int pivotIdx = start + rand.nextInt(end - start + 1);
        int pivotFinal = partition(arr, start, end, freq, pivotIdx);

        if (pivotFinal == kIndex) {
            return;                // done
        } else if (pivotFinal > kIndex) {
            quickSelect(arr, start, pivotFinal - 1, kIndex, freq);
        } else {
            quickSelect(arr, pivotFinal + 1, end, kIndex, freq);
        }
    }

    private static int partition(ArrayList<Integer> keys,
                                 int start,
                                 int end,
                                 HashMap<Integer, Integer> freq,
                                 int pivotIdx) {
        // move pivot to end
        swap(keys, pivotIdx, end);
        int pivotFreq = freq.get(keys.get(end));

        int store = start;
        // everything <= pivot goes left
        for (int i = start; i < end; i++) {
            if (freq.get(keys.get(i)) <= pivotFreq) {
                swap(keys, i, store);
                store++;
            }
        }
        // put pivot in its final spot
        swap(keys, store, end);
        return store;
    }
}
