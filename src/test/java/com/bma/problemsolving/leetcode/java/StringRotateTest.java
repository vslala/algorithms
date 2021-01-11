package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringRotateTest {

    @ParameterizedTest
    @CsvSource({
            "abcde,cdeab,true",
            "abcde,abced,false"
    })
    void givenTwoStringsAAndBFindThatTheGivenStringBCanBeFormedByLeftShiftingTheStringAWith_NaiveSolution(String first, String second, boolean expected) {
        var rotateString = new StringRotate();
        boolean output = rotateString.bruteForce(first, second);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "abcde,cdeab,true",
            "abcde,abced,false"
    })
    void givenTwoStringsAAndBFindThatTheGivenStringBCanBeFormedByLeftShiftingTheStringA_Optimized(String first, String second, boolean expected) {
        var rotateString = new StringRotate();
        boolean output = rotateString.rotateString(first, second);
        assertEquals(expected, output);
    }

}