package com.bma.fixtures;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import lombok.experimental.UtilityClass;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            try {
                newArr.add(Integer.parseInt(String.valueOf(c)));
            } catch (NumberFormatException e) {
                newArr.add(String.valueOf(c));
            }
            parseExpression(expression, index + 1, newArr, result);
        }
    }

    public static <T> void assertBothListsContainsSameItems(List<List<T>> expected, List<List<T>> result) {
        try {
            expected.forEach(ls -> ls.forEach(num ->
                    assertTrue(result.stream().anyMatch(ls2 -> ls2.stream().anyMatch(num2 -> num2.equals(num))))));
        } catch (AssertionFailedError e) {
            Util.println("Expected :" + expected);
            Util.println("Actual\t :" + result);
            throw new AssertionFailedError(e.getMessage());
        }
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

    public static char[][] convertToPrimitiveCharMatrix(List<List<String>> input) {
        char[][] output = new char[input.size()][input.get(0).size()];
        for (var i = 0; i < input.size(); i++) {
            output[i] = new char[input.get(i).size()];
            for (var j = 0; j < input.get(i).size(); j++) {
                output[i][j] = input.get(i).get(j).charAt(0);
            }
        }

        return output;
    }

    public static void assertArrayEquals(int[] original, int[] expectedResult, int[] result) {
        System.out.println("Result:");
        System.out.println(Arrays.stream(original).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(expectedResult).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(",")));

        assertEquals(expectedResult.length, result.length);

        var i = 0;
        var j = 0;
        while (i < expectedResult.length) {
            assertEquals(expectedResult[i++], result[j++]);
        }
    }

    public static void assertBothMatrixContainsSameItems(int[][] original, int[][] expected, int[][] actual) {
        Util.println("Original:");
        Util.printMatrix(original, "|");

        Util.println("\nExpected:");
        Util.printMatrix(expected, "|");

        Util.println("\nResult:");
        Util.printMatrix(actual, "|");

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }

    public static void assertBothCharMatrixContainsSameItems(char[][] original, char[][] expected, char[][] actual) {
        Util.println("Original:");
        Util.printMatrix(original, "|");

        Util.println("\nExpected:");
        Util.printMatrix(expected, "|");

        Util.println("\nResult:");
        Util.printMatrix(actual, "|");

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }

    public static int[] convertListToArray(List<Integer> result) {
        return result.stream()
                .mapToInt(item -> item)
                .toArray();
    }

    public static List<Integer> convertArrayToList(int[] original) {
        return Arrays.stream(original)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> parseNestedArrExpression(String expression, Type type) {
        return LeetCodeInputExpressionParser.parseNestedArrExpression(expression, ',', type);
    }

    public static String[] convertListToStringArray(List<String> inputList) {
        return inputList.toArray(new String[0]);
    }

    public static List<String> convertStringArrayToList(String[] arr) {
        List<String> output = new ArrayList<>();
        Collections.addAll(output, arr);

        return output;
    }
}
