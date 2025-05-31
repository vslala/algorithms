package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 22/05/2025
 */
class LettersCombinationOfAPhoneNumber {
    private Map<Character, String> letters = Map.of(
            '2',
            "abc",
            '3',
            "def",
            '4',
            "ghi",
            '5',
            "jkl",
            '6',
            "mno",
            '7',
            "pqrs",
            '8',
            "tuv",
            '9',
            "wxyz"
    );
    private List<String> combinations;
    private String digits;
    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        this.combinations = new ArrayList<>();
        backtrack(0, new StringBuilder());
        return this.combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == this.digits.length()) {
            this.combinations.add(path.toString());
            return;
        }

        String possibleLetters = letters.get(digits.charAt(index));
        for (char letter: possibleLetters.toCharArray()) {
            path.append(letter);
            backtrack(index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
