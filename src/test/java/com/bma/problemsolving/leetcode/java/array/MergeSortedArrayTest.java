package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortedArrayTest {

    private MergeSortedArray sol = new MergeSortedArray();

    @ParameterizedTest
    @CsvSource({
        "1_2_3_0_0_0, 3, 2_5_6, 3, 1_2_2_3_5_6",
        "1, 1, _, 0, 1",
        "0, 0, 1, 1, 1",
    })
    void shouldMergeTwoSortedArrayIntoOneTheFirstArray(String nums1Str, int m, String nums2Str, int n, String expectedStr) {
        var nums1 = Fixtures.splitAndParseArr(nums1Str, "_");
        var nums2 = Fixtures.splitAndParseArr(nums2Str, "_");
        var expected = Fixtures.splitAndParseArr(expectedStr, "_");

        sol.merge(nums1, m, nums2, n);

        Fixtures.assertArrayEquals(expected, nums1);
    }

}