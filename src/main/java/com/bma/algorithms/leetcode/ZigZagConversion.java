package com.bma.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Ref: https://leetcode.com/problems/zigzag-conversion/
 */
public class ZigZagConversion {

    public String convert(String testStr, int numRows) {
        if (numRows <= 1) return testStr;
        // init  rows
        var rows = new ArrayList<List<Character>>(numRows);
        IntStream.range(0, numRows).forEach(row -> rows.add(new ArrayList<>(4)));

        char[] charArr = testStr.toCharArray();
        int size = testStr.length();

        boolean reverse = false;
        for (int charIndex = 0, rowCount = 0; charIndex < size; charIndex++) {

            rows.get(rowCount).add(charArr[charIndex]);

            if (reverse)    rowCount--;
            else            rowCount++;

            if (rowCount >= numRows - 1) reverse = true;
            if (rowCount == 0) reverse = false;
        }

        return rows.stream().map(row  ->  row.stream().map(String::valueOf).collect(Collectors.joining(""))).collect(Collectors.joining());
    }
}
