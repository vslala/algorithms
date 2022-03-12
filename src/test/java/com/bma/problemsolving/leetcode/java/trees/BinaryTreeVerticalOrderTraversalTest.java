package com.bma.problemsolving.leetcode.java.trees;

import com.bma.algorithms.misc_datastructures.BinaryTree;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class BinaryTreeVerticalOrderTraversalTest {

    BinaryTreeVerticalOrderTraversal sol = new BinaryTreeVerticalOrderTraversal();

    @ParameterizedTest
    @CsvSource(value = {
            "[[3,9,20,null,null,15,7]] [[9],[3,15],[20],[7]]",
            "[[3,9,8,4,0,1,7]] [[4],[9],[3,0,1],[8],[7]]",
            "[[3,9,8,4,0,1,7,null,null,null,2,5]] [[4],[9,5],[3,0,1],[8,2],[7]]"
    }, delimiter = ' ')
    void shouldPerformVerticalOrderTraversal(String inputExpr, String expectedExpr) {
        List<List<Integer>> input = Fixtures.parseNestedArrExpression(inputExpr, Integer.class);
        List<List<Integer>> expected = Fixtures.parseNestedArrExpression(expectedExpr, Integer.class);

        BinaryTree btree = new BinaryTree(input.get(0));
        btree.print();
        List<List<Integer>> output = sol.verticalOrder(btree.get());

        Fixtures.assertBothListsContainsSameItems(expected, output);
    }
}