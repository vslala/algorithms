package com.bma.algorithms.sort.elementary;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Util {
    private Util() {}

    public static int[] generateUnsortedArray(int length) {
        Random rand = new Random();
        int[] output = new int[length];
        for (int i=0; i<length; i++) {
            output[i] = rand.nextInt(length);
        }
        return output;
    }

    public static void println(int[] input) {
        for (int i : input) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void swap(int[] input, int index1, int index2) {
        int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

    public static boolean less(int num1, int num2) {
        return num1 < num2;
    }

    public static void println(Object format) {
        System.out.println(format);
    }

    public static void print(Object obj) {
        System.out.print(obj);
    }

    public static void println(int[] input, int low, int high) {
        while (low < high)
            Util.print(input[low++] + ",");

        Util.println();
    }

    public static void println() {
        System.out.println();
    }

    public static void println(Collection collection) {
        collection.forEach(item -> Util.print(item));
        Util.println();
    }

    public static <T> boolean less(List<T> collection, int index1, int index2, Comparator comparator) {
        return comparator.compare(collection.get(index1), collection.get(index2)) < 0;
    }

    public static <T extends Comparable> boolean less(List<T> collection, int index1, int index2) {
        return collection.get(index1).compareTo(collection.get(index2)) < 0;
    }

    public static void measureRunTime(Runnable job) {
        long startMillis = System.currentTimeMillis();
        job.run();
        System.out.println("Total Time Taken: " + (System.currentTimeMillis() - startMillis) + "ms");
    }

    public static <I extends Comparable> void println(List<I> list, String delimiter) {
        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(delimiter)));
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Util.print(matrix[i][j] + ",");
            }
            Util.println();
        }
        Util.println();
    }
}
