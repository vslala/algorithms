package com.bma.algorithms.sort.elementary;

import com.bma.algorithms.sort.Sort;

/*
First we gap-sort the array (based on the gap-sequence).
Once the array is gap-sorted, we apply normal insertion sort
to sort the remaining array.
It takes much less calculations by insertion sort on the gap-sorted array.
 */
public class ShellSort implements Sort {

    private final int[] input;
    private long totalTime;

    public ShellSort(int[] input) {
        this.input = input;
    }

    public void time() {
        System.out.println(String.format("ShellSort>\t\t\tTotal Time Taken: %dms", totalTime));
    }

    public void print() {
        for (int num : input) {
            System.out.print(String.format("%d,", num));
        }
        System.out.println();
    }

    public void sort() {
        int arrLength = input.length;
        int gap = 1;

        // Calculate the Sedgewick's Gap sequence
        StringBuilder gapSequence = new StringBuilder("Gap Sequence: ")
                .append(gap).append(",");
        while (gap < arrLength / 3) {
            gap = 3 * gap + 1;
            gapSequence.append(gap)
                    .append(",");
        }
        System.out.println(gapSequence);

        long startMillis = System.currentTimeMillis();
        while (gap > 1) {
            // gap-sort the array
            for (int i = gap; i < arrLength; i++) {
                for (int j = i; j >= gap && less(input[j], input[j - gap]); j -= gap) {
                    swap(input, j, j - gap);
                }
            }

            gap = gap / 3;
        }

        // Normal insertion sort on the gap-sorted array
        InsertionSort insertionSort = new InsertionSort(input);
        insertionSort.sort();
        totalTime = System.currentTimeMillis() - startMillis;
        insertionSort.time();
    }

    private void swap(int[] input, int index1, int index2) {
        int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

    private boolean less(int num1, int num2) {
        return num1 < num2;
    }

    public static void main(String[] args) {
        int[] input =  Util.generateUnsortedArray(10000000); //new int[]{10,9,8,7,6,5,4,3,2,1};
        ShellSort shellSort = new ShellSort(input);
        shellSort.sort();
//        shellSort.print();
        shellSort.time();
    }
}
