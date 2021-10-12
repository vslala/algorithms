package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.HashMap;
import java.util.Map;

class WordSearch {

    public boolean exist(char[][] board, String word) {
        var root = new TrieNode();
        createTrie(word, root);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfsWithTrie(board, i, j, root)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfsWithTrie(char[][] board, int i, int j, TrieNode root) {
        if (outOfBounds(board, i, j) || root == null || !root.children.containsKey(board[i][j])) {
            return false;
        }
        if (!root.children.get(board[i][j]).content.isEmpty()) {
            return true;
        }

        var temp = board[i][j];
        board[i][j] = '$';
        var result = dfsWithTrie(board, i + 1, j, root.children.get(temp)) ||
                dfsWithTrie(board, i - 1, j, root.children.get(temp)) ||
                dfsWithTrie(board, i, j + 1, root.children.get(temp)) ||
                dfsWithTrie(board, i, j - 1, root.children.get(temp));
        board[i][j] = temp;

        return result;
    }

    private void createTrie(String word, TrieNode root) {
        var curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.children.computeIfAbsent(c, v -> new TrieNode());
        }
        curr.content = word;
    }


    private boolean dfsSolution(char[][] board, String word) {
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int currCharCount, int row, int col) {
        if (currCharCount == word.length()) {
            return true;
        }

        if (outOfBounds(board, row, col) || board[row][col] != word.charAt(currCharCount)) {
            return false;
        }

        // cannot use the same char twice, temporary erasing the cell  to avoid repeating it
        var tempChar = board[row][col];
        board[row][col] = ' ';
        var found = dfs(board, word, currCharCount + 1, row + 1, col) ||
                dfs(board, word, currCharCount + 1, row - 1, col) ||
                dfs(board, word, currCharCount + 1, row, col + 1) ||
                dfs(board, word, currCharCount + 1, row, col - 1);

        board[row][col] = tempChar;
        return found;
    }

    private boolean outOfBounds(char[][] board, int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[row].length;
    }

    private class TrieNode {
        Map<Character, TrieNode> children;
        String content;

        private TrieNode() {
            children = new HashMap<>();
            content = "";
        }

        public TrieNode(Map<Character, TrieNode> children, String content) {
            this.children = children;
            this.content = content;
        }
    }
}
