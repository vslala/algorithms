package com.bma.problemsolving.leetcode.java.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseWordInString3Test {

    private ReverseWordInString3 sol = new ReverseWordInString3();

    @ParameterizedTest
    @CsvSource({
            "Let's take LeetCode contest, s'teL ekat edoCteeL tsetnoc",
            "God Ding, doG gniD"
    })
    void shouldReverseEachWordOfTheSentence(String sentence, String expected) {
        var result = sol.reverseWords(sentence);
        assertEquals(expected, result);
    }
}