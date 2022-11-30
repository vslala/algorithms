package com.bma.problemsolving.leetcode.java.contest;

import com.bma.fixtures.Fixtures;
import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DailyChallengeTest {

    private DailyChallenge.RandomizedSet randomizedSet;

    @BeforeEach
    void setUp() {
        randomizedSet = new DailyChallenge.RandomizedSet();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "[RandomizedSet,insert(0),insert(1),remove(0),insert(2),remove(1),getRandom] [null,true,true,true,true,true,2]",
            "[RandomizedSet,insert(1),remove(2),insert(2),getRandom,remove(1),insert(2),getRandom] [null,true,false,true,1,true,false,2]",
            "[RandomizedSet,remove(0),remove(0),insert(0),getRandom,remove(0),insert(0)] [null,false,false,true,0,true,true]"
    }, delimiter = ' ')
    void itShouldPerformCorrectOperationsInO1Time(String input, String expected) {
        // Given:
        List<String> outputs = new ArrayList<>();
        String[] tokens = extractTokens(input);
        for (String token : tokens) {
            if (token.startsWith("RandomizedSet")) {
                randomizedSet = new DailyChallenge.RandomizedSet();
                outputs.add("null");
            } else if (token.startsWith("insert")) {
                int start = token.indexOf("(") + 1;
                int end = token.length() - 1;
                int number = Integer.parseInt(token.substring(start, end));
                outputs.add(String.valueOf(randomizedSet.insert(number)));
            } else if (token.startsWith("remove")) {
                int start = token.indexOf("(") + 1;
                int end = token.length() - 1;
                int number = Integer.parseInt(token.substring(start, end));
                outputs.add(String.valueOf(randomizedSet.remove(number)));
            } else if (token.startsWith("getRandom")) {
                outputs.add(String.valueOf(randomizedSet.getRandom()));
            }
        }

        // Then:
        String[] expectedTokens = extractTokens(expected);
        assertEquals(Arrays.stream(expectedTokens).collect(Collectors.toList()), outputs);
    }

    private static String[] extractTokens(String input) {
        return input.replace("[", "")
                .replace("]", "")
                .split(",");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2,2,1,1,3]] true",
            "[[1,2]] false",
            "[[-3,0,1,-3,1,1,1,-3,10,0]] true"
    }, delimiter = ' ')
    void testIfTheNumberOccurrencesAreUnique(String input, boolean expectedOutput) {
        List<List<Integer>> parsedInput = LeetCodeInputExpressionParser.parseNestedArrExpression(input, ',', Integer.class);
        int[] finalInput = Fixtures.convertListToArray(parsedInput.get(0));

        DailyChallenge.UniqueNumberOfOccurrences uniqueNumberOfOccurrences = new DailyChallenge.UniqueNumberOfOccurrences();
        var output = uniqueNumberOfOccurrences.uniqueOccurrences(finalInput);

        assertEquals(expectedOutput, output);
    }
}