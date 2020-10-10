package com.bma.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Ref. https://leetcode.com/problems/string-to-integer-atoi/
 */
public class ATOI {

    private int pointer = 0;
    private int startPointer = -1;
    private int endPointer = -1;

    final Map<Character, Integer> charToIntMap = new HashMap<>();
    {
                charToIntMap.put('0', 0);
                charToIntMap.put('1', 1);
                charToIntMap.put('2', 2);
                charToIntMap.put('3', 3);
                charToIntMap.put('4', 4);
                charToIntMap.put('5', 5);
                charToIntMap.put('6', 6);
                charToIntMap.put('7', 7);
                charToIntMap.put('8', 8);
                charToIntMap.put('9', 9);
                charToIntMap.put('-', -1);
                charToIntMap.put('+', 0);
    }

    public int parse(String input) {
        skipWhiteSpaces(input);

        initStartPointer(input);
        if (startPointer == -1) return 0; // contains invalid char

        initEndPointer(input);
        if (endPointer == -1) return 0;

        long output = calcOutput(input);

        output = checkOutputBoundaryCondition(output);

        reset();

        return (int) output;
    }

    private void reset() {
        pointer = 0;
        startPointer = -1;
        startPointer = -1;
    }

    private long checkOutputBoundaryCondition(long output) {
        if (output < Integer.MIN_VALUE)
            output = Integer.MIN_VALUE;
        if (output > Integer.MAX_VALUE)
            output = Integer.MAX_VALUE;
        return output;
    }

    private long calcOutput(String input) {
        long output = 0L;
        int counter = 0;
        while (endPointer >= startPointer) {
            final int val = charToIntMap.get(input.charAt(endPointer--));
            if (val == -1)
                output *= -1;
            else
                output += val * Math.pow(10, counter++);
        }
        return output;
    }

    private void initEndPointer(String input) {
        final int length = input.length();
        int operationCount = 0;
        boolean insideLoop = false;
        while (pointer < length && charToIntMap.containsKey(input.charAt(pointer))) {
            if (input.charAt(pointer) == '-' || input.charAt(pointer) == '+') {
                // handle strings like "00000-42a1234"
                // operator cannot come after number
                if (insideLoop) {
                    endPointer = -1;
                    break;
                }
                operationCount++;
                if (operationCount == 2) {
                    endPointer = -1;
                    break;
                }
            }
            insideLoop = true;
            pointer++;
        }
        endPointer = pointer - 1;
    }

    private void initStartPointer(String input) {
        final int length = input.length();
        if (pointer < length && charToIntMap.containsKey(input.charAt(pointer)))
            startPointer = pointer;
    }

    private void skipWhiteSpaces(String input) {
        final int length = input.length();
        while (pointer < length) {
            if (input.charAt(pointer) == ' ') pointer++;
            else break;
        }
    }
}
