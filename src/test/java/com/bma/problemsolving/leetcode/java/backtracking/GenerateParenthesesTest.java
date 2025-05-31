package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.fixtures.Fixtures;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpioneer.jupiter.json.JsonSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 22/05/2025
 */
class GenerateParenthesesTest {

    @Data
    static class Input {
        int n;
        List<String> expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
            {
                "n": 3,
                "expected": ["((()))","(()())","(())()","()(())","()()()"]
            }
            """,
            """
            {
                "n": 1,
                "expected": ["()"]
            }
            """
    })
    void it_should_generate_all_combinations_of_parentheses_of_given_number_of_pair(Input input) {
        var sol = new GenerateParentheses();
        List<String> output = sol.generateParenthesis(input.n);
        Fixtures.assertBothListsContainsSameItems(input.expected, output);
    }

}