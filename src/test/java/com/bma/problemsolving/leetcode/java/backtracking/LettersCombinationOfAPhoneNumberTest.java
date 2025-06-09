package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.fixtures.Fixtures;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;

import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 22/05/2025
 */
class LettersCombinationOfAPhoneNumberTest {

    @Data
    static class Input {
        String digits;
        List<String> expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "digits": "23",
                        "expected": ["ad","ae","af","bd","be","bf","cd","ce","cf"]
                    }
                    """,
            """
                    {
                        "digits": "",
                        "expected": []
                    }
                    """,
            """
                    {
                        "digits": "2",
                        "expected": ["a","b","c"]
                    }
                    """
    })
    void it_should_generate_all_possible_combinations_of_the_digits(Input input) {
        var sol = new LettersCombinationOfAPhoneNumber();
        List<String> output = sol.letterCombinations(input.digits);
        Fixtures.assertBothListsContainsSameItems(input.expected, output);
    }

}