package com.bma.algorithms.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZigZagConversionTest {

    @ParameterizedTest
    @CsvSource({
            "PAYPALISHIRING,PAHNAPLSIIGYIR,3",
            "PAYPALISHIRING,PINALSIGYAHRPI,4",
            "AB,AB,1",
            "ABC,ABC,1",
            "ABC,ABC,3",
            "ABC,ACB,2",
            "ABC,ABC,0",
    })
    void itShouldPrintZigZagString(String testStr, String expectedOutput, int numRows) {
        var zigZagConversion = new ZigZagConversion();
        String output = zigZagConversion.convert(testStr, numRows);
        assertEquals(expectedOutput, output);
    }

}