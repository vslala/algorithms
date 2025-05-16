package com.bma.problemsolving.leetcode.java.dailychallenge;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

class LongestUnequalAdjacentGroupsSequencePartTwoTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[bab,dab,cab] [1,2,2] [bab,dab]",
            "[a,b,c,d] [1,2,3,4] [a,b,c,d]",
            "[a] [1] [a]"
    }, delimiter = ' ')
    void should_return_the_longest_subsequence_of_words_who_have_same_length_and_one_hamming_distance_away_and_different_groups(String inputExpr, String groupsExpr, String expectedExpr) {
        String[] words = Fixtures.parse1DString(inputExpr);
        int[] groups = Fixtures.parse1DArray(groupsExpr);
        List<String> expected = Arrays.asList(Fixtures.parse1DString(expectedExpr));

        var sol = new LongestUnequalAdjacentGroupsSequencePartTwo();
        List<String> output = sol.getWordsInLongestSubsequence(words, groups);

        Fixtures.assertBothListsContainsSameItems(expected, output);
    }

}