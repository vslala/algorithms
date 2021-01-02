package com.bma.problemsolving.codewars;

import java.util.LinkedList;
import java.util.Map;

/*
URL: https://www.codewars.com/kata/5277c8a221e209d3f6000b56/train/java
 */
public class BraceChecker {

    private static final Map<Character, Character> pairs = Map.of(
            '(', ')',
            '{', '}',
            '[', ']'
    );

    public boolean isValid(String braces) {
        System.out.println(braces);
        if (braces.length() % 2 != 0)
            return false;

        var stack = new LinkedList<Character>();
        for (char c : braces.toCharArray())
            if (pairs.containsKey(c)) stack.push(c);
            else if (stack.isEmpty() || !pairs.get(stack.pop()).equals(c)) return false;

        return stack.isEmpty();
    }
}
