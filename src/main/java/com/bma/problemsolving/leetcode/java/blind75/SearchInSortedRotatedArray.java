package com.bma.problemsolving.leetcode.java.blind75;


class SearchInSortedRotatedArray {
    /**
     * Since the array is rotated, it is wise to first find the pivot point from where the array has been rotated
     * and then perform binary search on both sides one after the other
     *
     * Worst case complexity will come out to be: O(find pivot + binary search first half + binary search second half)
     *      --> O(log n + log (n - pivot) + log (n - (n - pivot)))
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int pivotIndex = findPivotIndex(nums);
        int result = binarySearch(nums, 0, pivotIndex + 1, target);
        if (result == -1) {
            return binarySearch(nums, pivotIndex, nums.length, target);
        }

        return result;
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
        while (low < high) {
            int mid = low + ((high - low) / 2);
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return -1;
    }

    private int findPivotIndex(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + ((high - low) / 2);
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }
}
