package com.bma.problemsolving.leetcode;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeArr = mergeSortedArrays(nums1, nums2);

        int arrIndexLen = mergeArr.length - 1;
        double nBy2 = mergeArr[arrIndexLen / 2];
        double nPlus1By2 = mergeArr[ (arrIndexLen + 1) / 2];

        if (isEvenLengthArr(mergeArr)) {
            return (nBy2 + nPlus1By2) / 2; // n/2 + (n+1)/2
        }

        return nBy2;
    }

    private int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        int arrayOneLength = nums1.length;
        int arrayTwoLength = nums2.length;
        int mergeArr[] = new int[arrayOneLength + arrayTwoLength];
        for (int i=0, j=0, k=0; i < arrayOneLength || j < arrayTwoLength;) {
            if (j >= arrayTwoLength) mergeArr[k++] = nums1[i++];
            else if (i >= arrayOneLength) mergeArr[k++] = nums2[j++];
            else if (nums1[i] < nums2[j]) mergeArr[k++] = nums1[i++];
            else mergeArr[k++] = nums2[j++];
        }
        return mergeArr;
    }

    private boolean isEvenLengthArr(int[] mergeArr) {
        return mergeArr.length % 2 == 0;
    }
}
