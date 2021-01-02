package com.bma.problemsolving.codewars;

/*
URL: https://www.codewars.com/kata/56606694ec01347ce800001b/train/java
A triangle is valid if sum of its two sides is greater than the third side.
If three sides are a, b and c, then three conditions should be met.
 */
class TriangleTester{
    public static boolean isTriangle(int a, int b, int c){
        if (a + b > c && a + c > b && b + c > a)
            return true;

        return false;
    }
}
