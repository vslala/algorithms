package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordBreakTest {
    private WordBreak wordBreak;

    @BeforeEach
    void setup() {
        wordBreak = new WordBreak();
    }

    @ParameterizedTest
    @CsvSource({
            "leetcode, leet_code, true",
            "applepenapple, apple_pen, true", // words can be reused multiple times from the dict
            "catsandog, cats_dog_sand_and_cat, false"
    })
    void shouldCheckIfTheGivenWordCanBeSegmentedIntoTheSequenceGivenInTheWordDict(String s, String wordDictStr, boolean expected) {
        List<String> wordDict = List.of(wordDictStr.split("_"));

        var result = wordBreak.bottomUpDPApproach(s, wordDict);
        var topDownResult = wordBreak.topDownMemoizationApproach(s, wordDict);

        assertEquals(expected, result);
        assertEquals(result, topDownResult);
    }
}