package com.bma.algorithms.sort.elementary;

import java.util.Random;

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

    public static void println(String format) {
        System.out.println(format);
    }

    public static void print(String str) {
        System.out.print(str);
    }

    public static void println(int[] input, int low, int high) {
        while (low < high)
            Util.print(input[low++] + ",");

        Util.println();
    }

    public static void println() {
        System.out.println();
    }
}
