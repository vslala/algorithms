package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PalindromeNumberTest {

    @ParameterizedTest
    @CsvSource({
            "121,true",
            "-121,false",
            "-101,false",
            "10,false",
            "59180,false",
            "100001,true",
    })
    void itShouldReturnTrueForPalindromeNumberAndFalseOtherwise(int input, String expectedOutput)  {
        var palindrome = new PalindromeNumber();
        boolean output = palindrome.check(input);
        assertEquals(Boolean.parseBoolean(expectedOutput), output);
    }

    @Test
    void sandbox() {
        int size = getSize(121) - 1;
        System.out.println((int) Math.pow(10, size));
        System.out.println(121 / (int) Math.pow(10, size));
    }

    int getSize(int number) {
        if (number == Integer.MIN_VALUE)
            number = Integer.MAX_VALUE;
        else
            number = Math.abs(number);
        int size = 0;
        for (int i=1; i < Integer.MAX_VALUE; i++) {
            if (number < Math.pow(10, i)) {
                size = i;
                break;
            }
        }

        return size;
    }

}