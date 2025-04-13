package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ATOITest {

    @Test
    void itShouldSkipWhiteSpaceCharsFromTheStartOfTheString() {
        var atoi = new ATOI();
        int output = atoi.parse("     42");
        assertEquals(42, output);
    }

    @Test
    void itShouldReturnZeroIfTheFirstCharAfterWhiteSpaceIsNonNumeric() {
        var atoi = new ATOI();
        int output = atoi.parse("           Invalid 456");
        assertEquals(0, output);
    }

    @Test
    void itShouldParseTheIntegerIfItComesBeforeInvalidString() {
        var atoi = new ATOI();
        int output = atoi.parse("           456 valid ");
        assertEquals(456, output);
    }

    @Test
    void itShouldParseTheNegativeNumberAsWell() {
        var atoi = new ATOI();
        int output = atoi.parse("-45");
        assertEquals(-45, output);
    }

    @Test
    void itShouldReturnIntegerMinValueIfTheNumberIsSmallerThanMinAndViceVersa() {
        var atoi = new ATOI();
        int output = atoi.parse("-91283472332");
        assertEquals(-2147483648, output);
    }

    @Test
    void itShouldReturnZeroInCaseOfEmptyStringInput() {
        var atoi = new ATOI();
        int output = atoi.parse(" ");
        assertEquals(0, output);
    }

    @ParameterizedTest
    @CsvSource({
            "     42,42",
            "    -42,-42",
            "words and 987,0",
            "-91283472332,-2147483648",
            "91283472332,2147483647",
            "4193 with words,4193",
            "+1,1",
            "+-12,0",
            "00000-42a1234,0",
    })
    void testForBoundaryConditionsWithMultipleInputs(String input, int expectedOutput) {
        var atoi = new ATOI();
        int output = atoi.parse(input);

        assertEquals(expectedOutput,  output);
    }


    @Test
    void sandbox() {
        System.out.println(42 * (1000* -1));
    }

}