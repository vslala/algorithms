package com.bma.problemsolving.leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PalindromeNumber {

    int getSize(int number) {
        if (number == Integer.MIN_VALUE)
            number = Integer.MAX_VALUE;
        else
            number = Math.abs(number);

        int size = 0;
        for (int i=1; i < Integer.MAX_VALUE; i++) {
            if (number < Math.pow(10, i)) {
                size = i;
                break;
            }
        }

        return size;
    }

    public boolean check(int input) {
        if (input < 0) return false;
        final int size = getSize(input);
        final int[] store = new int[size];
        int counter = 0;

        int ans = input;
        while (ans != 0) {
            int digit = ans % 10;
            ans /= 10;
            store[counter++] = digit;
        }

        int left = 0;
        int right = store.length - 1;
        while (left < right) {
            if (store[left++] != store[right--])
                return false;
        }

        System.out.println(Arrays.stream(store).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        return true;
    }
}
