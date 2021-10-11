package com.bma.fixtures;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author varun.shrivastava
 */
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

    /**
     * Parses a nested list expression like [[2_2_2_2]:[2_3_3]:[3_5]]
     *
     * @param expression
     * @param <T>
     * @return
     */
    public static <T> List<T> parseExpression(String expression) {
        var result = new ArrayList<>();
        parseExpression(expression, 0, new ArrayList<>(), result);
        return (List<T>) result;
    }

    static void parseExpression(String expression, int index, List<Object> newArr, List<Object> result) {
        if (index == expression.length()) {
            return;
        }

        char c = expression.charAt(index);
        if (c == '[') {
            newArr = new ArrayList<>();
            parseExpression(expression, index + 1, newArr, result);
        } else if (c == '_' || c == ':') {
            parseExpression(expression, index + 1, newArr, result);
        } else if (c == ']') {
            result.add(newArr);
            parseExpression(expression, index + 1, newArr, result);
        } else {
            newArr.add(Integer.parseInt(String.valueOf(c)));
            parseExpression(expression, index + 1, newArr, result);
        }
    }

    public static void assertBothListsContainsSameItems(List<List<Integer>> expected, List<List<Integer>> result) {
        expected.forEach(ls -> ls.forEach(num -> {
            assertTrue(result.stream().anyMatch(ls2 -> ls2.stream().anyMatch(num2 -> num2.equals(num))));
        }));
    }
}
