package com.bma.problemsolving.leetcode.java;

/**
 * Quick Reference Guide for Binary search
 *
 * @author varun.shrivastava
 */
class BinarySearchProblems {


    public int usualBS(int[] arr, int toFind) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (arr[mid] == toFind) return mid;
            else if (arr[mid] < toFind) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }

    public int iterativeJumpBS(int[] arr, int toFind) {
        int n = arr.length;
        int k = 0;
        for (int jump = n / 2; jump > 0; jump = jump / 2) {
            while (k + jump < n && arr[k + jump] <= toFind) k += jump;
        }

        return arr[k] == toFind ? k : -1;
    }

    public int lowerBound(int[] arr, int key) {
        int n = arr.length;
        int k = -1;
        for (int jump = n; jump > 0; jump /= 2) {
            while (k + jump < n && arr[k + jump] < key) {
                k += jump;
            }
        }

        if (k + 1 >= arr.length || arr[k + 1] != key) return -1;

        return k + 1;
    }

    public int upperBound(int[] arr, int key) {
        int n = arr.length;
        int k = arr[0];
        for (int jump = n; jump > 0; jump /= 2) {
            while (k + jump < n && arr[k + jump] <= key) {
                k += jump;
            }
        }

        // this is to handle case 6 (when key is not found)
        if (arr[k] != key && k + 1 == n) return -1;

        return k;
    }
}
