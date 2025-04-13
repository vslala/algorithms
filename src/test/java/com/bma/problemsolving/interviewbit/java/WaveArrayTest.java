package com.bma.problemsolving.interviewbit.java;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaveArrayTest {

    @Test
    void itShouldArrangeTheArrayItemsInAWave() {
        final var input = Arrays.asList(1,2,3,4);
        final List<Integer> expected = Arrays.asList(2, 1, 4, 3);
        var testClass = new WaveArray();

        List<Integer> output = testClass.wave(input);

        assertEquals(expected, output);
    }

}