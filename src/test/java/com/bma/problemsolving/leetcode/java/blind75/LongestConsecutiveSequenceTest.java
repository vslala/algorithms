package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestConsecutiveSequenceTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[100,4,200,1,3,2]] 4",
            "[[0,3,7,2,5,8,4,6,0,1]] 9",
            "[[1,0,1,2]] 3",
            "[[1,0,1,2]] 3",

    }, delimiter = ' ')
    void should_return_the_longest_consequence_sequence_from_the_array(String arrInput, int expected) {
        sol = new LongestConsecutiveSequence();
        List<List<Integer>> input = LeetCodeInputExpressionParser.parseNestedArrExpression(arrInput, ',', Integer.class);
        int output = sol.longestConsecutive(input.getFirst().stream().mapToInt(Integer::intValue).toArray());

        assertEquals(expected, output);
    }

    private LongestConsecutiveSequence sol;

}