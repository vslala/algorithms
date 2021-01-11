package com.bma.problemsolving.codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeExtractorTest {

    @Test
    public void test_BasicTests() {
        var rangeExtractor = new RangeExtractor();

        assertEquals("-6,-3-1,3-5,7-11,14,15,17-20", rangeExtractor.rangeExtraction(new int[] {-6,-3,-2,-1,0,1,3,4,5,7,8,9,10,11,14,15,17,18,19,20}));
        assertEquals("-12,-9,-6,-4,-2,0-4,6,9-11,14,16,18,19,21,24,25,27,29,32,34,35,38,39", rangeExtractor.rangeExtraction(new int[] {-12,-9,-6,-4,-2,0,1,2,3,4,6,9,10,11,14,16,18,19,21,24,25,27,29,32,34,35,38,39,}));
        assertEquals("-3--1,2,10,15,16,18-20", rangeExtractor.rangeExtraction(new int[] {-3,-2,-1,2,10,15,16,18,19,20}));
        assertEquals("-19,-16,-13,-10,-7,-6,-3,-1,0,2,5,8,9,11,13,15,17,20,22,25,26,29,31,32,34", rangeExtractor.rangeExtraction(new int[] {-19,-16,-13,-10,-7,-6,-3,-1,0,2,5,8,9,11,13,15,17,20,22,25,26,29,31,32,34}));
    }


}