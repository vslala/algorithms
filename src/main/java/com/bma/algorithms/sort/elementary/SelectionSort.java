package com.bma.algorithms.sort.elementary;

import com.bma.algorithms.sort.Sort;

public class SelectionSort implements Sort {

    private int[] input;
    private long totalTime;

    public SelectionSort(int[] input) {
        this.input = input;
    }

    public void sort() {
        long startTime = System.currentTimeMillis();
        for (int index = 0; index < input.length; index++) {
            int min = input[index];
            for (int innerLoop = index + 1; innerLoop < input.length; innerLoop++)
                if (min > input[innerLoop])
                    min = swap(input, index, innerLoop);
        }
        totalTime = System.currentTimeMillis() - startTime;
    }

    private int swap(int[] input, int index, int innerLoop) {
        int min;
        min = input[innerLoop];
        input[innerLoop] = input[index];
        input[index] = min;
        return min;
    }

    public void time() {
        System.out.println(String.format("SelectionSort>\t\t\tTotal Time Taken: %sms", totalTime));
    }

    public void print() {
        for (int i : input) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] input = Util.generateUnsortedArray(100000);
        SelectionSort selectionSort = new SelectionSort(input);
        selectionSort.sort();
        selectionSort.time();
    }
}
