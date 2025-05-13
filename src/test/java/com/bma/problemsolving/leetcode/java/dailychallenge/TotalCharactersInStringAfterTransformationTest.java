package com.bma.problemsolving.leetcode.java.dailychallenge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TotalCharactersInStringAfterTransformationTest {

    @ParameterizedTest
    @CsvSource(value = {
            "abcyy 2 7",
            "azbk 1 5",
            "jqktcurgdvlibczdsvnsg 7517 79033769"
    }, delimiter = ' ')
    void given_a_string_it_should_perform_the_transformations_and_return_the_final_length_of_the_string(String s, int t, int expected) {
        var sol = new TotalCharactersInStringAfterTransformation();
        int output = sol.lengthAfterTransformations(s, t);

        assertEquals(expected, output);
    }
}