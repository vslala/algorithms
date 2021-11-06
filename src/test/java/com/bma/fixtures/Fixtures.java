package com.bma.fixtures;

import com.bma.algorithms.sort.elementary.Util;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
     * @param expression format [[2_2_2_2]:[2_3_3]:[3_5]]
     * @param <T> returns depends on the caller List<List<T>>
     * @return defaults List<List<Object>>
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseExpression(String expression) {
        var result = new ArrayList<>();
        parseExpression(expression, 0, new ArrayList<>(), result);
        result.remove(result.size() - 1);

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
        expected.forEach(ls -> ls.forEach(num -> assertTrue(result.stream().anyMatch(ls2 -> ls2.stream().anyMatch(num2 -> num2.equals(num))))));
    }

    public static int[][] convertToPrimitiveArrMatrix(List<List<Integer>> ls) {
        var result = new int[ls.size()][];
        for (var i = 0; i < ls.size(); i++) {
            result[i] = new int[ls.get(i).size()];
            for (var j = 0; j < ls.get(i).size(); j++) {
                result[i][j] = ls.get(i).get(j);
            }
        }

        return result;
    }

    public static void assertArrayEquals(int[] original, int[] expectedResult, int[] result) {
        assertEquals(expectedResult.length, result.length);

        System.out.println("Result:");
        System.out.println(Arrays.stream(original).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(expectedResult).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(",")));

        var i = 0;
        var j = 0;
        while (i < expectedResult.length) {
            assertEquals(expectedResult[i++], result[j++]);
        }
    }

    public static void assertBothMatrixContainsSameItems(int[][] original, int[][] expected, int[][] actual) {
        Util.printMatrix(original, "|");
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }
}
