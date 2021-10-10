package com.bma.fixtures;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class Fixtures {

    public static void measureRunTime(Runnable job) {
        long startMillis = System.currentTimeMillis();
        job.run();
        System.out.println("Total Time Taken = " + (System.currentTimeMillis() - startMillis) + "ms");
    }

    public static int[] splitAndParseArr(String str, String splitter) {
        if (str.trim().equals("_")) return new int[0];
        return Arrays.stream(str.split(splitter)).mapToInt(Integer::parseInt).toArray();
    }

    public static char[][] createMatrix(String matrixStr, String colSplitter, String rowSplitter) {
        var rows = matrixStr.split(rowSplitter);
        var matrix = new char[rows.length][rows.length];
        for (int i = 0; i < rows.length; i++) {
            var cols = rows[i].replaceAll(colSplitter, "").toCharArray();
            matrix[i] = cols;
        }

        return matrix;
    }
}
