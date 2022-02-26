package com.bma.problemsolving.leetcode.java.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Find the nearest smaller element for each array element, that is, the first smaller element that precedes the element in the array.
 * It is possible that no such element exists, in which case the algorithm should report this.
 * Notes: https://1drv.ms/u/s!Aqs4QKD1-0HcwlrnPZSUnfVWNbWB?e=xL9OCq
 *
 * @author Varun Shrivastava
 */
class NearestSmallerElement {

    public int[] nearestSmaller(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < len; i++) {
            int num = arr[i];
            if (stack.isEmpty()) {
                result[i] = -1;
                stack.push(num);
            } else if (stack.peek() < num) {
                result[i] = stack.peek();
                stack.push(num);
            } else {
                while (!stack.isEmpty() && stack.peek() >= num) {
                    stack.pop();
                }

                result[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(num);
            }
        }


        return result;
    }
}
