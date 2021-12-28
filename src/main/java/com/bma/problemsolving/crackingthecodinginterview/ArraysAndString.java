package com.bma.problemsolving.crackingthecodinginterview;

import java.util.HashMap;

public class ArraysAndString {

    public boolean determineIfStringHasAllUniqueCharacters(String input) {
        var charSet = new boolean[256];
        var i = 0;
        while (i < input.length()) {
            if (charSet[input.charAt(i)])
                return false;
            charSet[input.charAt(i)] = true;
            i++;
        }

        return true;
    }

    /**
     * Idea is to maintain the count of each character of the first string into a map
     * and then compare the count of each character of second string from the map
     * if they match then return true
     * else false
     *
     * Complexities
     * ---------------
     * space = O(n)
     * time = O(n)
     *
     * @param first
     * @param second
     * @return
     */
    public boolean isStringOnePermutationOfTheOther(String first, String second) {
        if (first.length() != second.length()) return false;

        var mem = new HashMap<Character, Integer>();

        var i = 0;
        while (i < first.length()) {
            if (mem.containsKey(first.charAt(i))) mem.put(first.charAt(i), mem.get(first.charAt(i)) + 1);
            else mem.put(first.charAt(i), 0);
            i++;
        }

        i = 0;
        while (i < second.length()) {
            if (mem.containsKey(second.charAt(i))) mem.put(second.charAt(i), mem.get(second.charAt(i)) - 1);
            else return false;
            i++;
        }

        return mem.values()
                .stream()
                .filter(item -> item > 0)
                .findFirst()
                .isEmpty();
    }

    public String urlify(String input) {
        var output = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) == ' ') output.append("%20");
            else output.append(input.charAt(i));
            i++;
        }
        return output.toString();
    }

    /**
     * In even string - each character should have the same count for a string to be a palindrome
     * In odd string  - each character should have the same count except for the middle character,
     *                  it's count should always be 1
     * @param input
     * @return
     */
    public boolean palindromePermutation(String input) {
        var mem = new HashMap<Character, Integer>();
        int i = 0;
        while (i < input.length()) {
            if (mem.containsKey(input.charAt(i))) {
                mem.put(input.charAt(i), mem.get(input.charAt(i)) + 1);
            } else {
                mem.put(input.charAt(i), 1);
            }
            i++;
        }

        // this is to avoid the IEdge case where first character repeats only one time
        var values = mem.values().toArray(new Integer[0]);
        var commonCount = Math.max(values[0], values[1]);

        boolean isEven = values.length % 2 == 0;

        i = 0;
        while (i < values.length) {
            if (values[i] != commonCount) {
                if (isEven) {
                    return false;
                } else {
                    if (values[i] != 1) {
                        return false;
                    }
                }
            }
            i++;
        }

        return true;

    }

    public boolean checkIfBothWordsAreOneOrZeroEditAway(String firstWord, String secondWord) {
        var i = 0;
        var j = 0;
        var misMatch = 0;

        if (firstWord.length() == secondWord.length()) {
            while (i < firstWord.length()) {
                if (firstWord.charAt(i) != secondWord.charAt(i))
                    misMatch++;
                i++;
            }

            return misMatch <= 1;
        }

        var biggerWord = firstWord.length() > secondWord.length() ? firstWord : secondWord;
        var smallerWord = firstWord.length() == biggerWord.length() ? secondWord : firstWord;
        if (biggerWord.length() - smallerWord.length() > 1) return false;

        i = 0;
        misMatch = 0;
        while (i < smallerWord.length()) {
            if (smallerWord.charAt(i) != biggerWord.charAt(j)) {
                misMatch++;
                if (misMatch > 1) return false;
            } else {
                i++;
            }
            j++;
        }

        return true;
    }

    public String compressText(String text) {
        var originalLength = text.length();

        var i = 0;
        var j = 0;
        var compressedText = new StringBuilder();
        while (j < originalLength) {
            if (text.charAt(i) != text.charAt(j)) {
                compressedText.append(text.charAt(i)).append(j - i);
                i = j;
            } else {
                j++;
            }
        }

        compressedText.append(text.charAt(i)).append(j - i);
        return originalLength <= compressedText.length() ? text : compressedText.toString();
    }

    public int[][] rotateMatrix90DegreeRight(int[][] matrix) {
        int i = 0; // 0
        int j = 0; // 0
        int n = matrix.length - 1; // 3
        while (i < (matrix.length + 1) / 2) {
            while (j < matrix.length / 2) {
                int temp = matrix[n - j][i]; // top-right
                // bottom-left = bottom-right
                matrix[n - j][i] = matrix[n - i][n - j];
                // top-left = bottom-left
                matrix[j][n - i] = matrix[n - j][i];
                // top-right = top-left
                matrix[n - j][i] = matrix[j][n - i];

                matrix[i][j] = temp;
                j++;
            }
            i++;
        }

        return matrix;
    }
}
