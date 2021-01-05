package com.bma.problemsolving.leetcode.java.infixtopostfix;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InfixToPostfixConverter {

    private static final Map<String, Operator> operations = Map.of(
            "/", new Division(),
            "*", new Multiplication(),
            "+", new Addition(),
            "-", new Subtraction()
    );
    public static final String SPACE = " ";

    private final ExpressionParser expressionParser;

    public InfixToPostfixConverter(ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    /**
     * Steps to convert infix expression to postfix
     * 1. Read the operands and append to the string
     * 2. If operator is encountered then:
     *  - if the stack is empty
     *      - push the operator to the stack
     *  - if stack is not empty
     *      -  check the precedence of the top of the stack operator and the current operator
     *          - if the precedence of the current operator is higher then push the operator to the stack
     *      -  if the precedence of the current operator is lower then top of the stack operator, then
     *          - pop the operator from the stack and append to the output
     *          - push the current operator to the stack
     *
     * 3. How to handle parenthesis?
     *
     *
     * @param infixExpression
     * @return
     */
    public String convert(String infixExpression) {
        List<String> tokens = expressionParser.parseInfix(infixExpression);
        var output = new StringBuilder();
        var stack = new LinkedList<Operator>();
        for (String token: tokens) {
            if (operations.containsKey(token))  {
                Operator curr = operations.get(token);
                if (stack.isEmpty()) stack.push(curr);
                else if (less(stack, curr)) {
                    output.append(stack.pop()).append(SPACE);
                    stack.push(curr);
                }
                else while (!stack.isEmpty() && !less(stack, curr)) output.append(stack.pop()).append(SPACE);
            } else output.append(token).append(SPACE);
        }

        while (!stack.isEmpty())
            output.append(stack.pop()).append(SPACE);

        return output.toString().trim();
    }

    private boolean less(LinkedList<Operator> stack, Operator curr) {
        return stack.peekFirst().getPrecedence().compareTo(curr.getPrecedence()) <= 0;
    }
}
