package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.util.Collections.swap;

public class TopKFrequentElements {

    private final Random rand = new Random();

    public int[] topKFrequent(int[] nums, int k) {

        var freqCounter = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freqCounter.put(num, freqCounter.getOrDefault(num, 0) + 1);
        }

        var keys = new ArrayList<>(freqCounter.keySet());

        quickSelect(keys, 0, keys.size() - 1, k, freqCounter);

        int[] output = new int[k];
        for (int i = 0, j = keys.size() - 1; i < k; i++) {
            output[i] = keys.get(j--);
        }

        return output;
    }

    private void quickSelect(ArrayList<Integer> keys, int start, int end, int k, HashMap<Integer, Integer> freqCounter) {
        if (start >= end)  return;

        int pivotIndex = start + rand.nextInt(end - start + 1);
        int storeIndex = partition(keys, start, end, freqCounter, pivotIndex);

        if (storeIndex == k) {
            return;
        } else if (storeIndex > k) {
            quickSelect(keys, start, storeIndex - 1, k, freqCounter);
        } else {
            quickSelect(keys, storeIndex + 1, end, k, freqCounter);
        }
    }

    private static int partition(ArrayList<Integer> keys, int start, int end, HashMap<Integer, Integer> freqCounter, int pivotIndex) {
        swap(keys, pivotIndex, end);
        int pivotFreq = freqCounter.get(keys.get(end));

        int storeIndex = start;
        for (int i = start; i < end; i++) {
            if (freqCounter.get(keys.get(i)) <= pivotFreq) {
                swap(keys, i, storeIndex);
                storeIndex++;
            }
        }

        swap(keys, storeIndex, end);
        return storeIndex;
    }
}
