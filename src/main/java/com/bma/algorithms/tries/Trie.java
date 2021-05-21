package com.bma.algorithms.tries;

class TrieNode {
    char c;
    int count;
    TrieNode[] nodes;

    public TrieNode() {
        nodes = new TrieNode[26];
    }

    public TrieNode(char c) {
        nodes = new TrieNode[26];
    }

    public void put(int key, TrieNode node) {
        nodes[key] = node;
        count++;
    }

    public TrieNode get(int key) {
        return nodes[key];
    }
}

public class Trie {

    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int key = c - 'a'; // limit the numbers within range of alphabets (26)
            if (curr.get(key) == null) {
                curr.put(key, new TrieNode(c));
            }
            curr = curr.get(key);
        }
    }

    public boolean query(String word) {
        boolean result = true;
        TrieNode curr = root;

        for (char c : word.toLowerCase().toCharArray()) {
            int key = c - 'a';
            if (curr.nodes[key] == null) {
                result = false;
                break;
            }
            curr = curr.nodes[key];

        }

        return result;
    }

    public String commonPrefix(String[] words) {
        if (words == null || words.length == 0)
            return "";
        if (words.length == 1)
            return words[0];

        String smallestWord = words[0];
        for (String word : words) {
            if (word.length() < smallestWord.length()) {
                smallestWord = word;
            }
            this.insert(word);
        }

        String word = smallestWord.toLowerCase();
        StringBuilder sb = new StringBuilder();
        TrieNode trieNode = root;

        findCommonPrefix(trieNode, 0, word.toCharArray(), sb);

        System.out.println(sb.toString());
        return sb.toString();
    }

    private void findCommonPrefix(TrieNode node, int level, char[] word, StringBuilder sb) {
        if (level < word.length && node.count == 1) {
            final char c = word[level++];
            sb.append(c);
            findCommonPrefix(node.get(c - 'a'), level,  word, sb);
        }
    }

}
