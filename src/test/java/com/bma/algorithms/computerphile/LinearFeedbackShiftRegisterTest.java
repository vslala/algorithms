package com.bma.algorithms.computerphile;

import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearFeedbackShiftRegisterTest {

    private LinearFeedbackShiftRegister feedbackShiftRegister = new LinearFeedbackShiftRegister("1001");

    @Test
    void shouldGenerateAllCombinationsOfRandomNumbersWithGivenBitLength() {
        var sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            int number = feedbackShiftRegister.nextInt();
            sb.append(number).append(",");
        }
        Util.println(sb);
        assertEquals("8287889910101515010305015", sb.toString());
    }

    @Test
    void sandbox() {
        var rand1 = new Random(1);
        var rand2 = new Random(1);

        for (int i = 0; i < 10; i++) {
            assertEquals(rand1.nextInt(), rand2.nextInt());
        }
    }
}