package com.bma.problemsolving.leetcode.java.dailychallenge;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

class LongestUnequalAdjacentGroupsSubsequencePartOneTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[e,a,b] [0,0,1] [e,b]",
            "[a,b,c,d] [1,0,1,1] [a,b,c]",
    }, delimiter = ' ')
    void should_return_the_longest_unequal_adjacent_group_subsequence(String wordExpr, String groupsExpr, String expectedExpr) {
        String[] words = Fixtures.parse1DString(wordExpr);
        int[] groups = Fixtures.parse1DArray(groupsExpr);
        String[] expected = Fixtures.parse1DString(expectedExpr);

        var sol = new LongestUnequalAdjacentGroupsSubsequencePartOne();
        List<String> output = sol.getLongestSubsequence(words, groups);

        Fixtures.assertBothListsContainsSameItems(Arrays.asList(expected), output);

    }
}