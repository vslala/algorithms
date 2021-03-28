package com.bma.problemsolving.interviewbit.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WaveArray {
    public List<Integer> wave(List<Integer> input) {
        Collections.sort(input);
        for (int i = 0; i < input.size() - 1; i += 2) {
            int temp = input.get(i);
            input.set(i, input.get(i+1));
            input.set(i + 1, temp);
        }

        return input;
    }
}
