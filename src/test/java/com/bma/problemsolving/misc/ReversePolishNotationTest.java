package com.bma.problemsolving.misc;

import com.bma.problemsolving.leetcode.java.ReversePolishNotation;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReversePolishNotationTest {

    @Test
    void itShouldReturnTheSumOfReversePolishNotion() {
        Map<String[], Integer> notations = Map.of(
                new String[]  {"4","3","-"}, 1,
                new String[]  {"2", "1", "+", "3", "*"}, 9,
                new String[]  {"4", "13", "5", "/", "+"}, 6,
                new String[]  {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22
        );

        notations.forEach((notation, expected) ->
                assertEquals(expected, new ReversePolishNotation().eval(notation)));

    }

}