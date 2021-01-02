package com.bma.problemsolving.codewars;

import java.util.stream.IntStream;

/*
URL: https://www.codewars.com/kata/5679aa472b8f57fb8c000047/train/java
You are going to be given an array of integers.
Your job is to take that array and find an index N
where the sum of the integers to the left of N is equal
to the sum of the integers to the right of N.
If there is no index that would make this happen, return -1.

1,2,3,4,3,2,1
pivot = 3
leftPointer = 0..pivot
rightPointer = N - 1...pivot
 */
public class EqualSidesOfAnArray {
    public static int findEvenIndex(int[] arr) {
        int pivot = -1;
        while (pivot < arr.length) {
            if (calcLeftSum(arr, pivot) - calcRightSum(arr, pivot) == 0)
                return pivot;

            pivot++;
        }

        return -1;
    }

    private static int calcRightSum(int[] arr, int pivot) {
        int rightSum = 0;
        for (int j = arr.length - 1; j > pivot; j--) {
            rightSum += arr[j];
        }
        return rightSum;
    }

    private static int calcLeftSum(int[] arr, int pivot) {
        int leftSum = 0;
        for (int i = 0; i < pivot; i++) {
            leftSum += arr[i];
        }
        return leftSum;
    }

    public static int find(int[] arr) {
        return IntStream.range(0, arr.length)
                .filter(n -> IntStream.of(arr).limit(n).sum() == IntStream.of(arr).skip(n + 1).sum())
                .findFirst().orElse(-1);
    }
}
