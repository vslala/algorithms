package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.algorithms.misc_datastructures.BinaryTree;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BinaryTreeRightSideViewTest {

    private BinaryTreeRightSideView sol = new BinaryTreeRightSideView();

    @ParameterizedTest
    @CsvSource({
            "1_2_3_-1_5_-1_4, 1_3_5_4"
    })
    void shouldReturnTheValuesOfTheTreeInOrderFromTheRightView(String inputStr, String expectedStr) {
        int[] original = Fixtures.splitAndParseArr(inputStr, "_");
        var binaryTree = new BinaryTree(Fixtures.convertArrayToList(original));
        var expected = Fixtures.splitAndParseArr(expectedStr, "_");

        var result = sol.rightSideView(binaryTree.get());

        Fixtures.assertArrayEquals(original, expected, Fixtures.convertListToArray(result));
    }


}