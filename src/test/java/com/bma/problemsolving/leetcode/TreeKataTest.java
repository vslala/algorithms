package com.bma.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeKataTest {

    @Test
    void itShouldTraverseTreeBasedOnTheCommands() {
        var treeKata = new TreeKata(10, "left,right,left,left,left");
        treeKata.print();
    }

}