package com.bma.problemsolving.codewars.morsecode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MorseCodeDecoderTest {


    @ParameterizedTest
    @CsvSource({
            ".... . -.--   .--- ..- -.. ., HEY JUDE",
            "...---...,SOS",
            " . ,E"
    })
    void itShouldReturnTheDecodedStringWhenMorseCodeIsPassed(String input, String output) {
        var morseCodeDecoder = new MorseCodeDecoder();
        assertEquals(output, morseCodeDecoder.decode(input));
    }
}