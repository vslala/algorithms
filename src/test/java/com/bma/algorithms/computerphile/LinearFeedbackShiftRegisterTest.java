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
        assertEquals("10,15,1,3,5,14,2,6,11,12,4,13,7,8,9,10,15,1,3,5,14,2,6,11,12,4,13,7,8,9,10,15,1,3,5,14,2,6,11,12,4,13,7,8,9,10,15,1,3,5,", sb.toString());
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