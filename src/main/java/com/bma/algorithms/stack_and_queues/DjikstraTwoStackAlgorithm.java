package com.bma.algorithms.stack_and_queues;

/*
1. Create Value Stack
2. Create Operator Stack
3. Iterate through the given string of operation from the left
and skip the left operator.
Put the value in the value stack and operator in the operator stack.
4. When encounter right parenthesis (closing parenthesis), then
pop the value from value stack and operator from operator stack,
perform the operation.
5. Put the resulted value back in the value stack.

link: https://learning.oreilly.com/videos/algorithms-24-part-lecture/9780134384528/9780134384528-ALGS_algs4partI_L3-S6
 */
public class DjikstraTwoStackAlgorithm {



    CustomStack valueStack;
    CustomStack operatorStack;

    public DjikstraTwoStackAlgorithm() {
        valueStack = new CustomStack();
        operatorStack = new CustomStack();
    }

    private int solve(String input) {
        char[] allChars = input.toCharArray();
        for (int i=0; i<allChars.length; i++) {
            if (allChars[i] == '(') continue;
            else if (allChars[i] == '+') operatorStack.push(String.valueOf(allChars[i]));
            else if (allChars[i] == '*') operatorStack.push(String.valueOf(allChars[i]));
            else if (allChars[i] == ')') {
                int operandOne = Integer.parseInt(valueStack.pop());
                int operandTwo = Integer.parseInt(valueStack.pop());
                String operator = operatorStack.pop();

                if (operator.equals("*"))
                    valueStack.push(String.valueOf(operandOne * operandTwo));
                else if (operator.equals("+"))
                    valueStack.push(String.valueOf(operandOne + operandTwo));
            } else valueStack.push(String.valueOf(allChars[i]));

        }
        return Integer.parseInt(valueStack.pop());
    }

    public static void main(String[] args) {
        String input = "(5+((2+1)*(5+5)))";
        DjikstraTwoStackAlgorithm twoStackAlgorithm = new DjikstraTwoStackAlgorithm();
        int output = twoStackAlgorithm.solve(input);
        System.out.println("Output: " + output);
    }
}
