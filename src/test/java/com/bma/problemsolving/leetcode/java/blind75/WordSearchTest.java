package com.bma.problemsolving.leetcode.java.blind75;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 17/05/2025
 */
class WordSearchTest {

    @Data
    private static class WordSearchInput {
        private char[][] board;
        private String word;
        private boolean expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "board": [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
                        "word": "ABCCED",
                        "expected": true
                    }
                    """,
            """
                    {
                        "board": [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
                        "word": "SEE",
                        "expected": true
                    }
                    """,
            """
                    {
                        "board": [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
                        "word": "ABCD",
                        "expected": false
                    }
                    """,
            """
                    {
                        "board": [["a"]],
                        "word" : "a",
                        "expected": true
                    }
                    """,
            """
                    {
                        "board": [["a","a","a"],["A","A","A"],["a","a","a"]],
                        "word": "aAaaaAaaA",
                        "expected": true
                    }
                    """
    })
    void given_a_matrix_of_characters_and_a_word_find_out_of_that_word_can_be_formed_in_that_matrix(WordSearchInput input) {
        var sol = new WordSearch();
        boolean output = sol.exist(input.getBoard(), input.getWord());
        assertEquals(input.expected, output);
    }
}