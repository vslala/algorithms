package com.bma.problemsolving.leetcode.java.blind75;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordBreak {

    private static class TrieNode {
        int count;
        String word;
        Map<Character, TrieNode> trieNodes;

        public TrieNode() {
            this.count = 0;
            this.word = "";
            this.trieNodes = new HashMap<>();
        }

        public TrieNode get(char c) {
            return this.trieNodes.get(c);
        }

        public void put(char c) {
            if (!trieNodes.containsKey(c)) {
                this.trieNodes.put(c, new TrieNode());
            }
        }

        public void markWord(String word) {
            this.word = word;
            this.count += 1;
        }
    }

    public boolean wordBreakWithTrie(String s, List<String> wordDict) {
        TrieNode trie = buildTrie(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            if (!dp[i]) continue;
            TrieNode node = trie;
            for (int j = i; j < s.length(); j++) {
                node = node.get(s.charAt(j));
                if (node == null) break;
                if (null != node.word && !node.word.isEmpty()) {
                    dp[j + 1] = true;
                }
            }
        }

        return dp[s.length()];

    }

    private static TrieNode buildTrie(List<String> wordDict) {
        var trie = new TrieNode();
        for (String word : wordDict) {
            TrieNode curr = trie;
            for (char c: word.toCharArray()) {
                curr.put(c);
                curr = curr.get(c);
            }
            curr.markWord(word);
        }

        return trie;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); i++) {
            if (!dp[i]) continue;
            for (int j = i; j <= s.length(); j++) {
                if (wordDict.contains(s.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }

        return dp[s.length()];
    }
}
