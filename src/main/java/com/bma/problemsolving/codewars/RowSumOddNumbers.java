package com.bma.problemsolving.codewars;

/*
URL:

                 1
              3     5
           7     9    11
       13    15    17    19
    21    23    25    27    29
31    33    35    37    39    41


1, 8, 27, 64, 125, 216,
      3^3, 4^3, 5^3, 6^3

n(n-1)*n + 1



 */
class RowSumOddNumbers {
    public static int rowSumOddNumbers(int n) {
        return (int) Math.pow(n, 3);
    }
}
