package com.bma.problemsolving.codeforces;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfApartmentsTest {

    @ParameterizedTest
    @CsvSource({
            "30,10 0 0",
            "67,4 11 0"
    })
    void itShouldStoreMapOfAllTheNumbersTill1000(int input, String expectedOutput) {
        var testClass = new NumberOfApartments();
        String output = testClass.guess(input);
        assertEquals(expectedOutput, output);
    }

}