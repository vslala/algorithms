package com.bma.problemsolving.leetcode.java.dailychallenge;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 05/06/2025
 */
class LexicographicallySmallestEquivalentString {
    private static class UnionFind {
        private int[] parent;
        public UnionFind() {
            this.parent = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
        }

        public int find(int a) {
            if (parent[a] != a) {
                parent[a] = find(parent[a]); // path compression
            }
            return parent[a];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py) {
                return;
            } else if (px < py) {
                this.parent[py] = px;
            } else {
                this.parent[px] = py;
            }

        }
    }
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        var unionFind = new UnionFind();
        for (int i = 0; i < s1.length(); i++) {
            unionFind.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        var sb = new StringBuilder();
        for (char c: baseStr.toCharArray()) {
            int x = unionFind.find(c - 'a');
            sb.append((char)('a' + x));
        }

        return sb.toString();
    }


}
