package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlienDictionaryTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[wrt,wrf,er,ett,rftt] wertf",
            "[z,x] zx",
            "[z,x,z] _"
    }, delimiter = ' ')
    void it_should_return_the_unique_letters_in_the_new_alien_language_sorted_in_lexicographically_increasing_order_by_the_new_language_rules(String inputExpr, String expected) {
        String[] words = Fixtures.convertListToStringArray(Fixtures.parseStringArrExpression(inputExpr));
        expected = expected.equals("_") ? "" : expected;

        var sol = new AlienDictionary();
        String output = sol.alienOrder(words);

        assertEquals(expected, output);
    }

}