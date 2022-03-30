package com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExpressionParser {

    private static final Set<Character> operators = Set.of('(', ')', '/', '*', '+', '-');

    public List<String> parseInfix(String expression) {
        expression = expression.replaceAll("\\s+", "");
        var output = new ArrayList<String>();
        int pivot = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (operators.contains(c)) {
                final String token = expression.substring(pivot, i);
                if (!token.isEmpty())
                    output.add(token);

                pivot = i + 1;
                output.add(String.valueOf(c));
            }
        }

        if (pivot < expression.length())
            output.add(expression.substring(pivot));

        return output;
    }
}
