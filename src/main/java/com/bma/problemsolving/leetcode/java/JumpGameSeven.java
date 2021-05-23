package com.bma.problemsolving.leetcode.java;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGameSeven {
    private final int min;
    private final int max;
    private int cursor = 0;
    private final String input;
    private final Queue<Integer> validPositions = new LinkedList<>();

    public JumpGameSeven(String input, int min, int max) {
        this.input = input;
        this.min = min;
        this.max = max;
        if (!input.isBlank())
            validPositions.add(0);
    }

    private void evalNextValidIndices() {
        if (cursor + min < input.length() && input.charAt(cursor + min) == '0')
            validPositions.add(cursor + min);

        if (cursor + max < input.length() && input.charAt(cursor + max) == '0')
            validPositions.add(cursor + max);
    }

    public boolean isThereAPath() {
        while (cursor < input.length()) {
            if (cursor == input.length() - 1)
                return true;

            if (validPositions.isEmpty())
                break;

            evalNextValidIndices();
            cursor = validPositions.remove();
        }

        return false;
    }
}
