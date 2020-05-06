package com.bma.algorithms.sort.elementary;

import com.bma.algorithms.sort.Sort;

public class InsertionSort implements Sort {

    private int[] input;
    private long totalTime;

    public InsertionSort(int[] input) {
        this.input = input;
    }


    public void time() {
        System.out.println(String.format("InsertionSort>\t\t\tTotal Time Taken: %sms", totalTime));
    }

    public void sort() {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<input.length; i++) {
            for (int j=i; j>0; j--) {
                if (input[j] < input[j-1]) {
                    swap(input, j);
                } else {
                    break;
                }
            }
        }
        totalTime = System.currentTimeMillis() - startTime;
    }

    private void swap(int[] input, int j) {
        int swap = input[j];
        input[j] = input[j-1];
        input[j-1] = swap;
    }

    public void print() {
        Util.println(input);
    }

    public static void main(String[] args) {
        int[] input =  Util.generateUnsortedArray(100000); //new int[]{10,9,8,7,6,5,4,3,2,1};
        InsertionSort insertionSort = new InsertionSort(input);
        insertionSort.sort();
        insertionSort.print();
        insertionSort.time();
    }
}
