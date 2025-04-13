package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;

class TreeKataTest {

    @Test
    void itShouldTraverseTreeBasedOnTheCommands() {
        var treeKata = new TreeKata(10, "left,right,left,left,left");
        treeKata.print();
    }

}