package com.bma.problemsolving.adventofcode2025;

import java.util.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 04/12/2025
 *
 * @aoc2025 <a href="https://adventofcode.com/2025/day/3">Day 3: Lobby</a>
 */
class Day3Lobby {

    private int[] findMax(String line, int startIndex, int endIndex) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (line.charAt(i) - '0' > max) {
                max = line.charAt(i) - '0';
                maxIndex = i;
            }
        }

        return new int[]{max, maxIndex};
    }

    public int part1(List<String> input) {
        int output = 0;
        for (String batteryBank : input) {
            int[] first = findMax(batteryBank, 0, batteryBank.length() - 1);
            int[] second = findMax(batteryBank, first[1] + 1, batteryBank.length());
            output += (first[0] * 10) + second[0];
        }

        return output;
    }

    public long part2(List<String> input) {
        long sum = 0;
        for (String line : input) {
            sum += monotonicIncreasingStack(line);
        }
        return sum;
    }

    private long monotonicIncreasingStack(String line) {
        int toRemove = line.length() - 12;

        Deque<Character> stack = new ArrayDeque<>();

        for (char digit : line.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() < digit && toRemove > 0) {
                stack.pop();
                toRemove--;
            }
            stack.push(digit);
        }

        // Remove any remaining digits from the end if needed
        while (toRemove > 0) {
            stack.pop();
            toRemove--;
        }

        // Build result from bottom of stack (keep only 12 digits)
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty() && result.length() < 12) {
            result.append(stack.removeLast());
        }

        return Long.parseLong(result.toString());
    }
}
