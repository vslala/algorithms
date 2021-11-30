package com.bma.problemsolving.leetcode.java.shortestpath;

import com.bma.algorithms.sort.elementary.Util;

import java.util.*;

class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return new BFS().ladderLength(beginWord, endWord, wordList);
    }

    private static boolean hasOneCharDifference(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    private static Map<String, List<String>> buildAdjacencyListMatrix(List<String> wordList) {
        final Map<String, List<String>> adjacencyListMatrix = new HashMap<>();

        for (int i = 0; i < wordList.size(); i++) {
            var firstWord = wordList.get(i);
            for (int j = 0; j < wordList.size(); j++) {
                var secondWord = wordList.get(j);
                if (hasOneCharDifference(firstWord, secondWord)) {
                    adjacencyListMatrix.computeIfAbsent(firstWord, word -> new ArrayList<>())
                            .add(secondWord);
                }
            }
        }

        Util.println(adjacencyListMatrix);
        return adjacencyListMatrix;
    }

    static class DFS {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            wordList.add(0, beginWord);

            buildAdjacencyListMatrix(wordList);

            var paths = new ArrayList<List<String>>();
            var aux = new ArrayList<String>();
            var visited = new HashSet<String>();
            var adjMatrix = buildAdjacencyListMatrix(wordList);
            dfs(beginWord, endWord, paths, aux, visited, adjMatrix);

            System.out.println(paths);

            return findMin(paths);
        }

        private int findMin(ArrayList<List<String>> paths) {
            int min = Integer.MAX_VALUE;
            for (List<String> path : paths) {
                if (path.size() < min) {
                    min = path.size();
                }
            }

            return min == Integer.MAX_VALUE ? 0 : min;
        }

        /**
         * Find all possible paths from start word to end word
         * @param word
         * @param endWord
         * @param paths
         * @param aux
         * @param visited
         * @param adjMatrix
         */
        private void dfs(String word, String endWord, List<List<String>> paths, List<String> aux, HashSet<String> visited, Map<String, List<String>> adjMatrix) {
            aux.add(word);
            visited.add(word);
            for (String connectedWord : adjMatrix.getOrDefault(word, List.of())) {
                if (!visited.contains(connectedWord)) {
                    dfs(connectedWord, endWord, paths, aux, visited, adjMatrix);
                    if (connectedWord.equals(endWord)) {
                        paths.add(new ArrayList<>(aux));
                    }
                    aux.remove(aux.size() - 1);
                    visited.remove(connectedWord);
                }
            }
        }
    }

    static class BFS {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!beginWord.equals(wordList.get(0))) {
                wordList.add(0, beginWord);
            }

            var set = new HashSet<>();

            var q = new LinkedList<String>();
            q.offer(beginWord);

            var adjWordMatrix = buildAdjacencyListMatrix(wordList);
            var distance = 0;
            while (!q.isEmpty()) {
                distance++;
                var size = q.size();

                while (size > 0) {
                    var currWord = q.poll();

                    assert currWord != null;
                    if (currWord.equals(endWord)) {
                        return distance;
                    }

                    var adjWords = adjWordMatrix.getOrDefault(currWord, new ArrayList<>());
                    for (String adjWord: adjWords) {
                        if (!set.contains(adjWord)){
                            set.add(adjWord);
                            q.offer(adjWord);
                        }
                    }

                    size--;
                }
            }

            return 0;
        }
    }

}
