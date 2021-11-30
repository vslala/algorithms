package com.bma.problemsolving.leetcode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * 1268. Search Suggestions System
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 *
 * @author varun.shrivastava
 */
class SearchSuggestionSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        var result = new ArrayList<List<String>>();
        var trie = new Trie();
        Arrays.asList(products).forEach(trie::insert);

        String prefix = "";
        for (char each : searchWord.toCharArray()) {
            prefix += each;
            result.add(trie.getAllWordsPrefixedWith(prefix));
        }

        return result;
    }

    class Trie {
        class Node {
            boolean word;
            List<Node> children = Arrays.asList(new Node[26]);
        }

        Node root;
        Trie() {
            root = new Node();
        }

        void insert(String word) {
            var temp = root;
            for (char c: word.toCharArray()) {
                int index = c - 'a';
                if (isNull(temp.children.get(index))) {
                    temp.children.set(index, new Node());
                }
                temp = temp.children.get(index);
            }

            temp.word = true;
        }

        List<String> getAllWordsPrefixedWith(String prefix) {
            var temp = root;
            var result = new ArrayList<String>();
            for (char c: prefix.toCharArray()) {
                var index = c - 'a';
                if (isNull(temp.children.get(index))) {
                    return result;
                }
                temp = temp.children.get(index);
            }

            // execution is here because it found the prefix in the trie
            dfs(temp, prefix, result);

            return result;
        }

        private void dfs(Node node, String prefix, ArrayList<String> result) {
            if (result.size() >= 3) {
                return ;
            }
            if (node.word) {
                result.add(prefix);
            }
            for (char c = 'a'; c <= 'z'; c++) {
                var child = node.children.get(c - 'a');
                if (nonNull(child)) {
                    dfs(child, prefix + (c), result);
                }
            }
        }
    }
}
