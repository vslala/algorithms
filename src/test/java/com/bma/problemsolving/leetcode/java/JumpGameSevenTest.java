package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameSevenTest {

    @ParameterizedTest
    @CsvSource({
            "011010,2,3,true",
            "01101110,2,3,false"
    })
    void itShouldIdentifyAllPossiblePathsFromCurrentPosition(String input, int min, int max, boolean expected) {
        var game = new JumpGameSeven(input, min, max);
        boolean output = game.isThereAPath();
        assertEquals(expected, output);
    }

}