package com.bma.algorithms.leetcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntegerTest {

    RomanToInteger  romanToInteger = new RomanToInteger();

    @ParameterizedTest
    @CsvSource({
            "III,3",
            "IV,4",
            "IX,9",
            "LVIII,58",
            "MCMXCIV,1994",
    })
    void itShouldConvertRomanNumeralToInteger(String s, int result) {
        int output = romanToInteger.convert(s);
        assertEquals(result, output);
    }

}