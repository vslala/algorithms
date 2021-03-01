package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CountAndSayTest {

    @ParameterizedTest
    @CsvSource({
            "1,1",
            "4,1211",
            "5,111221"
    })
    void itShouldCountAndSay(int n,  String expected) {
        var testClass = new CountAndSay();
        var output = testClass.countAndSay(n);
        assertEquals(expected, output);
    }
}