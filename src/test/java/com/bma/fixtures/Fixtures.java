package com.bma.fixtures;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import lombok.experimental.UtilityClass;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
     * @param <T>        returns depends on the caller List<List<T>>
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

    public static List<List<String>> sortNestedList(List<List<String>> nestedList) {
        for (List<String> innerList : nestedList) {
            Collections.sort(innerList);
        }

        nestedList.sort(Comparator.comparing(List::getFirst));
        return nestedList;
    }

    public static <T extends Comparable<T>> void assertBothNestedListsContainsSameItems(List<List<T>> expected, List<List<T>> result) {
        try {
            List<List<T>> sortedExpected = normalizeNestedList(expected);
            List<List<T>> sortedResult = normalizeNestedList(result);

            assertEquals(sortedExpected, sortedResult);
        } catch (AssertionError e) {
            Util.println("Expected :" + expected);
            Util.println("Actual   :" + result);
            throw new AssertionError(e.getMessage());
        }
    }

    private static <T extends Comparable<T>> List<List<T>> normalizeNestedList(List<List<T>> list) {
        List<List<T>> normalized = new ArrayList<>();

        for (List<T> inner : list) {
            List<T> sortedInner = new ArrayList<>(inner);
            Collections.sort(sortedInner);
            normalized.add(sortedInner);
        }

        // sort outer list by the first element of each inner list:
        normalized.sort((l1, l2) -> {
            if (l1.isEmpty() && l2.isEmpty()) return 0;
            if (l1.isEmpty())              return -1;
            if (l2.isEmpty())              return +1;
            return l1.getFirst().compareTo(l2.getFirst());
        });

        return normalized;
    }



    public static <T> void assertBothListsContainsSameItems(List<T> expected, List<T> result) {
        try {
            expected.forEach(item -> assertTrue(result.contains(item)));
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

    public static List<String> parseStringArrExpression(String expr) {
        Pattern pattern = Pattern.compile("[^\\[\\],]+");
        Matcher matcher = pattern.matcher(expr);

        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group().trim());
        }

        return result;
    }

    public static int[] parse1DArray(String expr) {
        Pattern pattern = Pattern.compile("[^\\[\\],]+");
        Matcher matcher = pattern.matcher(expr);

        List<Integer> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(Integer.parseInt(matcher.group().trim()));
        }

        return convertListToArray(result);
    }

    public static void assertArrayContainsSameItems(int[] expected, int[] output) {
        Arrays.sort(expected);
        Arrays.sort(output);

        assertEquals(expected.length, output.length, "Arrays length are not equal. Expected: %d, Actual: %d".formatted(expected.length, output.length));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], output[i]);
        }
    }

    public static List<List<String>> parse2DString(String stringMatrixExpression) {
        if (stringMatrixExpression == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String expr = stringMatrixExpression.trim();
        if (!expr.startsWith("[") || !expr.endsWith("]")) {
            throw new IllegalArgumentException(
                    "Invalid 2D-list expression: " + stringMatrixExpression);
        }

        // strip the outermost [ ]
        String inner = expr.substring(1, expr.length() - 1).trim();
        List<List<String>> result = new ArrayList<>();

        // if there's nothing inside the outer [ ], we're done
        if (inner.isEmpty()) {
            return result;
        }

        // walk the string, pulling out every top-level “[ ... ]” segment
        int depth = 0, start = -1;
        for (int i = 0; i < inner.length(); i++) {
            char c = inner.charAt(i);
            if (c == '[') {
                if (depth == 0) {
                    start = i;
                }
                depth++;
            } else if (c == ']') {
                depth--;
                if (depth == 0 && start >= 0) {
                    // we've found one complete “[ ... ]” from start..i
                    String segment = inner.substring(start + 1, i).trim();
                    List<String> row = new ArrayList<>();
                    if (!segment.isEmpty()) {
                        // split on comma, trim each piece, skip any empty tokens
                        for (String tok : segment.split(",")) {
                            String w = tok.trim();
                            if (!w.isEmpty()) {
                                row.add(w);
                            }
                        }
                    }
                    result.add(row);
                }
            }
        }

        return result;
    }

    public static String[] parse1DString(String expr) {
        var ls = Fixtures.parseStringArrExpression(expr);
        return ls.toArray(new String[0]);
    }

    public static int[][] parse2DArray(String expr) {
        return convertToPrimitiveArrMatrix(parseNestedArrExpression(expr, Integer.class));
    }
}
