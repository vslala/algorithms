package com.bma.algorithms.sort.elementary;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({
        "unused",
        "squid:S106" // system out warnings
})
@Slf4j
public class Util {
    private static final Random rand = new Random();

    private Util() {}

    public static int[] generateUnsortedArray(int length) {

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
        System.out.println(System.lineSeparator());
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

    public static <T> void println(Collection<T> collection) {
        collection.forEach(Util::print);
        Util.println();
    }

    public static <T> boolean less(List<T> collection, int index1, int index2, Comparator<T> comparator) {
        return comparator.compare(collection.get(index1), collection.get(index2)) < 0;
    }

    public static <T extends Comparable<T>> boolean less(List<T> collection, int index1, int index2) {
        return collection.get(index1).compareTo(collection.get(index2)) < 0;
    }

    public static void measureRunTime(Runnable job) {
        long startMillis = System.currentTimeMillis();
        job.run();
        log.info("Total Time Taken: " + (System.currentTimeMillis() - startMillis) + "ms");
    }

    public static <I extends Comparable<I>> void println(List<I> list, String delimiter) {
        log.info(list.stream().map(String::valueOf).collect(Collectors.joining(delimiter)));
    }

    public static void printMatrix(char[][] matrix, String separator) {
        if (Objects.isNull(separator) || separator.isEmpty()) {
            separator = ",";
        }

        for (char[] chars : matrix) {
            for (char aChar : chars) {
                Util.print(aChar + separator);
            }
            Util.println();
        }
        Util.println();
    }

    public static boolean arrayEquals(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        // check segment length
        if (e1 - s1 != e2 - s2) {
            return false;
        }

        for (int i = s1, j = s2; i < e1; i++, j++) {
            if (arr1[i] != arr2[j]) {
                return false;
            }
        }

        return true;
    }

    public static void printMatrix(int[][] matrix, String separator) {
        if (Objects.isNull(separator) || separator.isEmpty()) {
            separator = ",";
        }

        for (int[] chars : matrix) {
            for (int aChar : chars) {
                Util.print(aChar + separator);
            }
            Util.println();
        }
        Util.println();
    }

    public static int max(int[] array, int startIndex, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = startIndex; i < end; i++) {
            if (max < array[i])
                max = array[i];
        }

        return max;
    }

    public static int min(int[] array, int startIndex, int endIndex) {
        int min = Integer.MAX_VALUE;
        for (int i = startIndex; i < endIndex; i++) {
            min = Math.min(min, array[i]);
        }

        return min;
    }
}
