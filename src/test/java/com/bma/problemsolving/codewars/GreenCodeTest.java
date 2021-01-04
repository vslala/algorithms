package com.bma.problemsolving.codewars;

import com.bma.problemsolving.codewars.qualified.GreenCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreenCodeTest {
    @Test
    public void test1() {

        Float[] cpuUsage = {0.3f, 0.5f, 0.9f, 0.7f, 0.1f};
        Integer[] usedHeap = {3000, 250, 100, 2000, 2000, 10246};

        assertTrue(GreenCode.verify(cpuUsage, usedHeap).equalsIgnoreCase("yellow"));
    }

    @Test
    public void test2() {

        Float[] cpuUsage = {0.3f, 0.5f, 0.8f, 0.7f, 0.1f};
        Integer[] usedHeap = {3000, 250, 22938, 2000, 2000};

        assertTrue(GreenCode.verify(cpuUsage, usedHeap).equalsIgnoreCase("red"));
    }

    @Test
    public void test3() {

        Float[] cpuUsage = {0.3f, 0.5f, 0.5f, 0.7f, 0.1f};
        Integer[] usedHeap = {3000, 4000, 7000, 2000, 2000};

        assertTrue(GreenCode.verify(cpuUsage, usedHeap).equalsIgnoreCase("yellow"));
    }

    @Test
    public void test4() {
        Float[] cpuUsage = {0.3f, 0.5f, null};
        Integer[] usedHeap = {3000, 4000, 7000, 2000, 2000};
        assertTrue(GreenCode.verify(cpuUsage, usedHeap).equalsIgnoreCase("red"));
    }

    @Test
    public void test5() {
        Float[] cpuUsage = {0.3f, 0.5f, 0.89f, 0.7f, null};
        Integer[] usedHeap = {3000, 250, 100, 100, 2000, 150};
        assertTrue(GreenCode.verify(cpuUsage, usedHeap).equalsIgnoreCase("green"));
    }

    @Test
    public void test6() {
        Float[] cpuUsage = {0.3f, 0.5f, 0.89f, 0.7f, 0.1f};
        Integer[] usedHeap = {3000, 250, 100, null, 2000, null};
        assertTrue(GreenCode.verify(cpuUsage, usedHeap).equalsIgnoreCase("red"));
    }

    @Test
    public void test7() {
        Float[] cpuUsage = {0.3f};
        Integer[] usedHeap = {3000};
        assertTrue(GreenCode.verify(cpuUsage, usedHeap).equalsIgnoreCase("green"));
    }
}