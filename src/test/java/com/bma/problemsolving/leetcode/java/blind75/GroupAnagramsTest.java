package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class GroupAnagramsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[eat,tea,tan,ate,nat,bat] [[bat],[nat,tan],[ate,eat,tea]]",
            "[] [[]]",
            "[a] [[a]]"
    }, delimiter = ' ')
    void given_array_of_words_return_the_words_in_a_group_that_forms_the_anagram(String inputStr, String expectedExpr) {
        String[] input = Fixtures.convertListToStringArray(Fixtures.parseStringArrExpression(inputStr));
        List<List<String>> expected = Fixtures.parse2DString(expectedExpr);

        var sol = new GroupAnagrams();
        List<List<String>> output = sol.groupAnagrams(input);

        Fixtures.assertBothNestedListsContainsSameItems(expected, output);
    }
}