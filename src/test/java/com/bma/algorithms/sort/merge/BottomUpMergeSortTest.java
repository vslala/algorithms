package com.bma.algorithms.sort.merge;

import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BottomUpMergeSortTest {

    @Test
    public void itShouldSortTheArrayInAscendingOrder() {
        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        int[] arrCopy = {1,2,3,4,5,6,7,8,9,10};
        BottomUpMergeSort sort = new BottomUpMergeSort(arr);
        sort.sort();

        Util.println(arr);
        assertArrayEquals(arr, arrCopy);
    }

    @Test
    public void longTestForSortingArray() {
        Random random = new Random();
        int numOfTestCases = random.nextInt(1000);
        int[] arr1;
        int[] arr2;
        BottomUpMergeSort mergeSort;

        for (int testCase = 0; testCase < numOfTestCases; testCase++) {
            arr1 = Util.generateUnsortedArray(random.nextInt(10000));
            arr2 = Arrays.copyOf(arr1, arr1.length);

            BottomUpMergeSort.getInstance().with(arr1).sort();
            Arrays.sort(arr2);

            assertArrayEquals(arr1, arr2);
        }

    }

}