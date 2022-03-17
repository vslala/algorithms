package com.bma.problemsolving.meta.array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationalCipherTest {
    private RotationalCipher sol = new RotationalCipher();

    @ParameterizedTest
    @CsvSource({
            "Zebra-493?, 3, Cheud-726?",
            "abcdefghijklmNOPQRSTUVWXYZ0123456789, 39, nopqrstuvwxyzABCDEFGHIJKLM9012345678"
    })
    void shouldReturnTheNewCipheredString(String input, int rotationFactor, String expected) {
        String output = sol.rotationalCipher(input, rotationFactor);

        assertEquals(expected, output);
    }

    @Test
    void sandbox() {

    }
}