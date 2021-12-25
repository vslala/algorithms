package com.bma.problemsolving.leetcode.java;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@UtilityClass
public class LeetCodeInputExpressionParser {

    public static <T> T parseNestedArrExpression(String expr, char delimiter, Type classType) {
        Deque<List<Object>> stack = new ArrayDeque<>();

        int index = 0;
        StringBuilder item = new StringBuilder();
        while (index < expr.length()) {
            char c = expr.charAt(index++);
            if (c == '[') {
                stack.push(new ArrayList<>());
            } else if (c == ']' && stack.size() > 1) {
                // end of inner arr -> `20190101,0,1]`
                addItemToArrWithProvideType(classType, stack, item);
                List<Object> top = stack.pop();
                assert stack.peek() != null;
                stack.peek().add(top);
            } else if (c == delimiter && stack.size() > 1) {
                // items in between brackets separated by delimiter -> 20190101,0,
                addItemToArrWithProvideType(classType, stack, item);
                item.setLength(0);
            } else if (c == delimiter) {
                // reset buffer
                item.setLength(0);
            } else {
                // add char to buffer
                item.append(c);
            }
        }

        return (T) stack.pop();
    }

    private static void addItemToArrWithProvideType(Type classType, Deque<List<Object>> stack, StringBuilder item) {
        if (item.toString().isEmpty())
            return;

        List<Object> top = stack.peek();
        assert top != null;
        switch (classType.getTypeName()) {
            case "java.lang.Integer":
                top.add(Integer.parseInt(item.toString().trim()));
                break;
            case "java.lang.Double":
                top.add(Double.parseDouble(item.toString().trim()));
                break;
            default:
                top.add(item.toString().trim());
                break;
        }
    }
}
