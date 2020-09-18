package com.bma.algorithms.sort.elementary;

import com.bma.algorithms.sort.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BubbleSort implements Sort {

    private int[] input;
    private long totalTime;

    public BubbleSort(int[] input) {
        this.input = input;
    }

    @Override
    public void print() {
        String sortedArray = Arrays.stream(input).mapToObj(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(sortedArray);
    }

    @Override
    public long time() {
        return totalTime;
    }

    @Override
    public void sort() {
        long startMillis = System.currentTimeMillis();
        IntStream.range(0, input.length).forEach(outerIndex -> {
            IntStream.range(0, outerIndex).forEach(innerIndex -> {
                if (input[innerIndex + 1] < input[innerIndex]) {
                    Util.swap(input, innerIndex,  innerIndex + 1);
                }
            });
        });
        totalTime = System.currentTimeMillis() - startMillis;
    }
}
