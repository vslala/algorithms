package com.bma.problemsolving.leetcode.java.design.basiccalculator.infixtopostfix;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InfixToPostfixConverter {

    private static final Map<String, Operator> OPERATORS = Map.of(
            "/", new Division(),
            "*", new Multiplication(),
            "+", new Addition(),
            "-", new Subtraction()
    );

    private static final Map<String, GroupingOperator> GROUPING_OPERATORS = Map.of(
            "(", new OpenParenthesis(),
            ")", new CloseParenthesis()
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
     * - if the stack is empty
     * - push the operator to the stack
     * - if stack is not empty
     * -  check the precedence of the top of the stack operator and the current operator
     * - if the precedence of the current operator is higher then push the operator to the stack
     * -  if the precedence of the current operator is lower then top of the stack operator, then
     * - pop the operator from the stack and append to the output
     * - push the current operator to the stack
     * <p>
     * 3. How to handle parenthesis?
     *
     * @param infixExpression
     * @return
     */
    public String convert(String infixExpression) {
        List<String> tokens = expressionParser.parseInfix(infixExpression);
        var output = new StringBuilder();
        var stack = new LinkedList<Operator>();
        for (String token : tokens) {
            // if current token is an arithmetic operator
            if (OPERATORS.containsKey(token)) {
                Operator curr = OPERATORS.get(token);
                // if the precedence of the current token is less than the top of the stack
                // then pop from the stack and append it to the output
                // then push the current token back into the stack
                while (!stack.isEmpty() && less(curr, stack.peekFirst()))
                    output.append(stack.pop()).append(SPACE);

                stack.push(curr);
            }

            // if the current token is a grouping operator
            else if  (GROUPING_OPERATORS.containsKey(token)) {
                GroupingOperator currOp = GROUPING_OPERATORS.get(token);
                if (currOp instanceof OpenParenthesis)
                    stack.push(currOp);

                // if the current token is a closing parenthesis ')'
                // then pop all the operators from the stack till you reach the opening parenthesis '('
                // and add them to the output expression
                else if (currOp instanceof CloseParenthesis) {
                    Operator curr;
                    while (! ((curr = stack.pop()) instanceof OpenParenthesis))
                        output.append(curr).append(SPACE);
                }
            }

            // if current token is an operand
            else output.append(token).append(SPACE);
        }

        // add remaining operators from stack to the output postfix expression
        while (!stack.isEmpty())
            output.append(stack.pop()).append(SPACE);

        return output.toString().trim();
    }

    private boolean less(Operator op1, Operator op2) {
        return op1.precedence().compareTo(op2.precedence()) <= 0;
    }

}
