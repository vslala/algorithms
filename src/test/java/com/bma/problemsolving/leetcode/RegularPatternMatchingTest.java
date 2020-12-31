package com.bma.problemsolving.leetcode;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RegularPatternMatchingTest {

    RegularPatternMatching regex = new RegularPatternMatching();

    @Test
    public void it_should_match_the_regular_expression_as_per_the_rules() {
        var byInputByAssertion = Map.of(
                List.of("aa", "a"), false,
                List.of("aa", "a*"), true,
                List.of("ab", ".*"), true,
                List.of("abcde", ".*"), true
        );

        byInputByAssertion.forEach((input, assertion) ->
                assertEquals(assertion, regex.isMatch(input.get(0), input.get(1))));
    }

}