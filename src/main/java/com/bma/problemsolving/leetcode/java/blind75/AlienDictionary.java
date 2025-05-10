package com.bma.problemsolving.leetcode.java.blind75;

import java.util.*;

class AlienDictionary {

    private static class Graph {
        private final Map<Character, Set<Character>> adjList = new HashMap<>();
        private final int[] inDegree = new int[26];

        private Graph(String[] words) {
            initAdjList(words);
            buildCharacterGraph(words);
        }

        /**
         * Initialize adjacency list matrix with each character as node with In-Degree 0
         * @param words
         */
        private void initAdjList(String[] words) {
            for (String word : words) {
                for (char c: word.toCharArray()) {
                    this.adjList.putIfAbsent(c, new HashSet<>());
                    setInDegree(c, 0);
                }
            }
        }

        /**
         * Build adjacency list matrix directed graph and keep count for the in-degrees of each node
         * Note: Only build graph until the first mismatch character e.g. if c1 != c2 then break;
         * @param words
         */
        private void buildCharacterGraph(String[] words) {
            for (int i = 0; i < words.length - 1; i++) {
                String first = words[i];
                String second = words[i + 1];

                if (first.length() > second.length() && first.startsWith(second)) {
                    this.adjList.clear();
                    break;
                }

                for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
                    char c1 = first.charAt(j);
                    char c2 = second.charAt(j);
                    if (c1 != c2) {
                        if (!this.adjList.get(c1).contains(c2)) {
                            this.adjList.get(c1).add(c2);
                            incrementInDegree(c2);
                        }
                        break;
                    }
                }
            }
        }

        /**
         * Sort the adjacency list graph in ascending order of their degrees and return the sorted word
         * first with characters (nodes) with 0 in-degrees
         * followed by nodes with 1 in-degree and so on...
         *
         * @return lexicographically sorted word
         */
        public String topologicalSort() {
            if (this.adjList.isEmpty()) {
                // invalid
                return "";
            }
            var q = new LinkedList<Character>();
            for (Character c : adjList.keySet()) {
                if (getInDegree(c) == 0) {
                    q.offer(c);
                }
            }

            var sb = new StringBuilder();
            while (!q.isEmpty()) {
                char c = q.poll();
                sb.append(c);
                for (char neighbour: this.adjList.get(c)) {
                    decrementInDegree(neighbour);
                    if (getInDegree(neighbour) == 0) {
                        q.offer(neighbour);
                    }
                }
            }

            if (sb.length() != this.adjList.size()) {
                // cycle detected
                return "";
            }

            return sb.toString();
        }

        private void decrementInDegree(char node) {
            this.inDegree[node - 'a'] -= 1;
        }

        private int getInDegree(Character c) {
            return this.inDegree[c - 'a'];
        }

        private void incrementInDegree(char c) {
            this.inDegree[c - 'a'] += 1;
        }

        private void setInDegree(char c, int val) {
            this.inDegree[c - 'a'] = val;
        }
    }

    public String alienOrder(String[] words) {
        var graph = new Graph(words);
        return graph.topologicalSort();
    }
}
