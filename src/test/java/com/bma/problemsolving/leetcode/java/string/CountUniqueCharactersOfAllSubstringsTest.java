package com.bma.problemsolving.leetcode.java.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountUniqueCharactersOfAllSubstringsTest {

    private CountUniqueCharactersOfAllSubstrings sol = new CountUniqueCharactersOfAllSubstrings();

    @ParameterizedTest
    @CsvSource({
//            "ABC, 10",
            "ABA, 8",
//            "LEETCODE, 92"
    })
    void shouldFindTheCountOfAllUniqueCharactersOfAllSubstrings(String s, int expected) {
        var result = sol.uniqueLetterString(s);
        assertEquals(expected, result);
    }

}