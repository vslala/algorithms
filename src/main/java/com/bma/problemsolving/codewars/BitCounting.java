package com.bma.problemsolving.codewars;

/*
URL: https://www.codewars.com/kata/526571aae218b8ee490006f4/train/java
 */
public class BitCounting {

    public static int countBits(int n){
        return (int) Integer.toBinaryString(n).chars().filter(c -> c == '1').count();
    }

}
