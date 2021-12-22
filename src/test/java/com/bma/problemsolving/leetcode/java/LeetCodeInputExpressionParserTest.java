package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeetCodeInputExpressionParserTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]] 20190101 5"
    }, delimiter = ' ')
    void shouldParseNestedArrayExpression(String expr, int i0j0, int i7j2) {
        List<List<Integer>> output = LeetCodeInputExpressionParser.parseNestedArrExpression(expr, ',', Integer.class);

        assertEquals(i0j0, output.get(0).get(0));
        assertEquals(i7j2, output.get(7).get(2));
    }
}