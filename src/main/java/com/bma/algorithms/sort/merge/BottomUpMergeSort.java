package com.bma.algorithms.sort.merge;

import com.bma.algorithms.sort.Sort;
import com.bma.algorithms.sort.elementary.Util;

import java.util.Objects;

public class BottomUpMergeSort implements Sort {

    private static BottomUpMergeSort mergeSort = new BottomUpMergeSort();
    private int[] input;
    private long totalTime;

    public BottomUpMergeSort(int[] arr) {
        this.input = arr;
    }

    public BottomUpMergeSort() {}

    public static BottomUpMergeSort getInstance() {
        if (Objects.isNull(BottomUpMergeSort.mergeSort)) {
            BottomUpMergeSort.mergeSort = new BottomUpMergeSort();
        }
        return BottomUpMergeSort.mergeSort;
    }

    public BottomUpMergeSort with(int[] input) {
        this.input = input;
        return BottomUpMergeSort.mergeSort;
    }

    @Override
    public void print() {
        Util.println(input);
    }

    @Override
    public long time() {
        Util.println("<BottomUpMergeSort>: Total Time: " + totalTime + "ms");
        return totalTime;
    }

    @Override
    public void sort() {
        long startMillis = System.currentTimeMillis();
        int N = input.length;
        int aux[] = new int[N];
        for (int arrSize = 1; arrSize < N; arrSize = arrSize + arrSize) {
            for (int low = 0; low < N-arrSize; low = low + arrSize + arrSize) {
                int start = low;
                int mid = low + arrSize;
                int end = Math.min(low + arrSize + arrSize, N);

                merge(input, aux, start, mid, end);
            }
        }
        totalTime = System.currentTimeMillis() - startMillis;
    }

    private void merge(int[] input, int[] aux, int start, int mid, int end) {
        int firstIndex = start;
        int secondIndex = mid;
        int index = start;

//        Util.println(input);
//        Util.println(String.format("Start: %d, Mid: %d, End: %d", start, mid, end));
//        Util.println(aux);
//        Util.println();

        while (firstIndex < mid || secondIndex < end) {
            if (firstIndex < mid && secondIndex >= end) aux[index++] = input[firstIndex++];
            else if (firstIndex >= mid && secondIndex < end) aux[index++] = input[secondIndex++];
            else if (input[firstIndex] <= input[secondIndex]) aux[index++] = input[firstIndex++];
            else if (input[secondIndex] <= input[firstIndex]) aux[index++] = input[secondIndex++];
        }

        for (index = start; index < end; index ++ ) {
            input[index] = aux[index];
        }
    }

    public static void main(String[] args) {
        int input[] = {23,12,45,12,45,67,32,2,4,6,743,1,21,12,12,1,32}; //Util.generateArrayInput(1000000);
        BottomUpMergeSort bottomUpMergeSort = new BottomUpMergeSort(Util.generateUnsortedArray(10000000));
        bottomUpMergeSort.sort();
//        bottomUpMergeSort2.print();
        bottomUpMergeSort.time();
    }
}
