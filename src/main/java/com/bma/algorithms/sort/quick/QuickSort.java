package com.bma.algorithms.sort.quick;

import com.bma.algorithms.sort.Sort;
import com.bma.algorithms.sort.elementary.Util;

import java.util.Random;

public class QuickSort implements Sort {

    private int input[];
    private long totalTime;
    private Random rand = new Random();

    public QuickSort(int[] input) {
        this.input = input;
    }

    @Override
    public void print() {
        Util.println(this.input);
    }

    @Override
    public long time() {
        Util.println("<QuickSort> Total Execution Time: " + totalTime + "ms");
        return totalTime;
    }

    @Override
    public void sort() {
        long startMillis = System.currentTimeMillis();
        int start = 0;
        int end = input.length;
        sort(input, start, end);
        totalTime = System.currentTimeMillis() - startMillis;
    }

    private void sort(int[] input, int start, int end) {
        if (start == end) return ;

        int randomNumber = rand.nextInt(end - start);
        int randomPivot = randomNumber + start;

        // move pivot at the end
        Util.swap(input, randomPivot, end-1);

        int pivot = end - 1;
        int i = start; int j = i;
        while (j < pivot) {
            if (input[j] < input[pivot]) {
                Util.swap(input, i, j);
                i++;
            }
            j++;
        }

        // put the pivot in its correct position
        Util.swap(input, i, pivot);
        pivot = i;

        sort(input, start, pivot);
        sort(input, pivot + 1, end);

    }

    private int sortWithConsoleLogs(int[] input, int start, int end) {

        if (start == end) return 0;

        Util.println(String.format("Start=%d End=%d", start, end));
        Util.println(input);

        int randomNumber = rand.nextInt(end - start);
        int randomPivot = randomNumber + start;
        Util.println("randomPivot=" + randomPivot);


        // move pivot at the end
        Util.swap(input, randomPivot, end-1);
        Util.println(input);

        int pivot = end - 1;
        int i = start; int j = i;
        while (j < pivot) {
            if (input[j] < input[pivot]) {
                Util.swap(input, i, j);
                i++;
            }
            j++;
        }

        // put the pivot in its correct position
        Util.swap(input, i, pivot);
        pivot = i;

        Util.println(String.format("i=%d j=%d", i, j));
        Util.println(input);
        Util.println("nth position=" + pivot);
        Util.println();

        int left = sortWithConsoleLogs(input, start, pivot);
        int right = sortWithConsoleLogs(input, pivot + 1, end);

        Util.println("-----------------------------------------");

        return pivot;
    }

    public static void main(String[] args) {
        int input[] = {10,9,8,7,6,5,4,3,2,1}; //Util.generateUnsortedArray(1000000);
        QuickSort quickSort = new QuickSort(Util.generateUnsortedArray(10000000));
        quickSort.sort();
//        quickSort.print();
        quickSort.time();
    }
}
