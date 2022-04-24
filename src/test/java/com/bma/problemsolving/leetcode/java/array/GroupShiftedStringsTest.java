package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class GroupShiftedStringsTest {

    private GroupShiftedStrings sol = new GroupShiftedStrings();

    @ParameterizedTest
    @CsvSource(value = {
            "[[abc,bcd,acef,xyz,az,ba,a,z]] [[acef],[a,z],[abc,bcd,xyz],[az,ba]]",
            "[[a]] [[a]]",
            "[[a,xc,yd]] [[a],[xc,yd]] "
    }, delimiter = ' ')
    void shouldGroupTheStringByTheShiftedCharacters(String inputStr, String expectedStr) {
        List<List<String>> input = LeetCodeInputExpressionParser.parseNestedArrExpression(inputStr, ',', String.class);
        List<List<String>> expected = LeetCodeInputExpressionParser.parseNestedArrExpression(expectedStr, ',', String.class);

        List<List<String>> output = sol.groupStrings(input.get(0).toArray(new String[0]));

        Fixtures.assertBothListsContainsSameItems(expected, output);
    }
}